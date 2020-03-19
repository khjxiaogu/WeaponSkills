package com.khjxiaogu.WeaponSkills;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.khjxiaogu.WeaponSkills.effect.EffectFactory;
import com.khjxiaogu.WeaponSkills.effect.EffectInstance;
import com.khjxiaogu.WeaponSkills.effect.PlayerEffects;
import com.khjxiaogu.WeaponSkills.skill.Skill;
import com.khjxiaogu.WeaponSkills.skill.SkillDescription;
import com.khjxiaogu.WeaponSkills.skill.SkillHook;
import com.khjxiaogu.WeaponSkills.skill.SkillInstance;
import com.khjxiaogu.WeaponSkills.skill.SkillPriority;

import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;

public class SkillEffectManager implements Listener {
	public static SkillEffectManager manager = new SkillEffectManager();
	private Map<String, EffectFactory> effects = new ConcurrentHashMap<>();
	private Map<Player, PlayerEffects> playereffect = new ConcurrentHashMap<>();
	private Map<String, Skill> skills = new ConcurrentHashMap<>();
	private NBTManager nm = NBTManager.getInstance();
	private Map<String, SkillHook> skillhooks = new ConcurrentHashMap<>();
	private SkillEffectManager() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (playereffect.isEmpty())
					return;
				for (PlayerEffects eff : playereffect.values()) {
							eff.onTick();
				}
			}
		}.runTaskTimerAsynchronously(WeaponSkills.plugin, 100, 5);
		// TODO Auto-generated constructor stub
	}
	public Skill getSkillByName(String s) {
		return skills.get(s);
	}
	public EffectFactory getEffectByName(String s) {
		return effects.get(s);
	}
	public boolean giveEffect(Player p, String name, int time, int level) {
		PlayerEffects current = playereffect.get(p);
		if (current == null) {
			current = new PlayerEffects(p);
			playereffect.put(p, current);
		}
		EffectFactory e = effects.get(name);
		if (e != null)
			current.giveEffect(e, time, level);
		else {
			WeaponSkills.plugin.getLogger().warning(name +WeaponSkills.plugin.locale.getEffectInvalid());
			return false;
		}
		return true;
	}

	public boolean giveEffect(Player p, EffectFactory e, int time, int level) {
		PlayerEffects current = playereffect.get(p);
		if (current == null) {
			current = new PlayerEffects(p);
			playereffect.put(p, current);
		}
		if (e != null)
			current.giveEffect(e, time, level);
		else {
			return false;
		}
		return true;
	}
	public EffectInstance getEffect(Player p,EffectFactory effect) {
		PlayerEffects current = playereffect.get(p);
		if (current != null) {
			return current.getEffect(effect);
		}
		return null;
	}
	public EffectInstance getEffect(Player p,String name) {
		PlayerEffects current = playereffect.get(p);
		EffectFactory e = effects.get(name);
		if (e == null)
			return null;
		if (current != null) {
			return current.getEffect(e);
		}
		return null;
	}
	public boolean hasEffect(Player p,EffectFactory effect) {
		PlayerEffects current = playereffect.get(p);
		if (current != null) {
			return current.hasEffect(effect);
		}
		return false;
	}
	public boolean hasEffect(Player p,String name) {
		PlayerEffects current = playereffect.get(p);
		EffectFactory e = effects.get(name);
		if (e == null)
			return false;
		if (current != null) {
			return current.hasEffect(e);
		}
		return false;
	}
	public void writeSkill(ItemStack item,Set<SkillDescription> skills) {
		NBTCompound nbt = nm.read(item);
		if (nbt == null)
			nbt = new NBTCompound();
		NBTCompound pnbt = nbt.getCompound("SkillEffects");
		if (pnbt == null)
			pnbt = new NBTCompound();
		String skillstr="";
		String levelstr="";
		for(SkillDescription skill:skills) {
			skillstr+=skill.getName();
			levelstr+=skill.getLevel();
			skillstr+=",";
			levelstr+=",";
		}
		pnbt.put("Skill",skillstr);
		pnbt.put("Skilllevel",levelstr);
		nbt.put("SkillEffects", pnbt);
		nm.write(item, nbt);
	}
	public void RegisterSkillHook(String name,SkillHook hook) {
		skillhooks.put(name,hook);
	}
	public void UnregisterSkillHook(String name) {
		skillhooks.remove(name);
	}
	public Set<SkillDescription> readSkill(ItemStack item) {
		Set<SkillDescription> ret=new HashSet<>();
		NBTCompound nbt = nm.read(item);
		if (nbt == null)
			return ret;
		NBTCompound pnbt = nbt.getCompound("SkillEffects");
		if (pnbt == null)
			return ret;
		String Skills = pnbt.getString("Skill");
		String levels = pnbt.getString("Skilllevel");
		String[] sks=Skills.split(",");
		String[] lvs=levels.split(",");
		
		for(int i=0;i<sks.length;i++) {
			ret.add(new SkillDescription(sks[i], skills.get(sks[i]),Integer.parseInt(lvs[i])));
		}
		return ret;
	}
	public SkillInstance getHookedSkills(Player p) {
		Set<SkillDescription> skills=new HashSet<>();
		for(SkillHook hook:skillhooks.values()) {
			try {
				skills.addAll(hook.getSkill(p));
			}catch(Exception e) {
				try {
					WeaponSkills.plugin.getLogger().warning("从"+hook.getName()+"载入技能失败!");
				}catch(Exception f) {}
				e.printStackTrace();
			}
		}
		return new SkillInstance(skills);
	}
	public SkillInstance getSkillInstance(ItemStack item) {
		if (item == null || item.getType() == Material.AIR)
			return null;
		NBTCompound nbt = nm.read(item);
		if (nbt == null)
			return null;
		NBTCompound pnbt = nbt.getCompound("SkillEffects");
		if (pnbt == null)
			return null;
		String Skills = pnbt.getString("Skill");
		String levels = pnbt.getString("Skilllevel");
		String[] sks=Skills.split(",");
		String[] lvs=levels.split(",");
		Skill[] skns=new Skill[sks.length];
		int[] lvns=new int[sks.length];
		for(int i=0;i<sks.length;i++) {
			skns[i]=skills.get(sks[i]);
			lvns[i]=Integer.parseInt(lvs[i]);
		}
		return new SkillInstance(skns,lvns);
	}

	public void removeSkill(ItemStack item) {
		NBTCompound nbt = nm.read(item);
		if (nbt == null)
			nbt = new NBTCompound();
		nbt.remove("SkillEffects");
		nm.write(item, nbt);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onInteractEntity(PlayerInteractEntityEvent ev) {
		SkillInstance handSkills = this.getSkillInstance(ev.getPlayer().getItemInHand());
		if(handSkills!=null)
			handSkills.onRightClickEntity(ev);
		getHookedSkills(ev.getPlayer()).onRightClickEntity(ev);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onInteractBlock(PlayerInteractEvent ev) {
		SkillInstance handSkills = this.getSkillInstance(ev.getPlayer().getItemInHand());
		if(handSkills!=null)
			handSkills.onRightClickBlock(ev);
		getHookedSkills(ev.getPlayer()).onRightClickBlock(ev);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	private void onDoDamage(EntityDamageByEntityEvent ev) {
		// handle the damager
		DoDamgeExecution(ev,SkillPriority.BeforeDispatch);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	private void onAfterDoDamage(EntityDamageByEntityEvent ev) {
		// handle the damager
		DoDamgeExecution(ev,SkillPriority.AfterDispatch);
	}
	private void DoDamgeExecution(EntityDamageByEntityEvent ev,SkillPriority pr) {
		if(ev.getCause()!=DamageCause.CUSTOM){
			if ((ev.getDamager() instanceof Player)) {
				Player p = (Player) ev.getDamager();
				// apply effect
				PlayerEffects pe = playereffect.get(p);
				if (pe != null)
					pe.onDoDamage(ev,pr);
				if(ev.isCancelled())return;
				// execute skill in hand
				SkillInstance handSkill = this.getSkillInstance(p.getItemInHand());
				if (handSkill != null)
					handSkill.onDoDamage(ev,pr);
				if(ev.isCancelled())return;
				getHookedSkills(p).onDoDamage(ev,pr);
				if(ev.isCancelled())return;
				// execute skill in armor
				for (ItemStack item : p.getEquipment().getArmorContents()) {
					SkillInstance armorSkill = this.getSkillInstance(item);
					if (armorSkill != null)
						armorSkill.onDoDamage(ev,pr);
					if(ev.isCancelled())return;
				}
			}
		}
		// now handle the damagee
		if (!(ev.getEntity() instanceof Player))
			return;
		Player p2 = (Player) ev.getEntity();
		// apply effect
		PlayerEffects pe2 = playereffect.get(p2);
		if (pe2 != null)
			pe2.onBeDamagedByEntity(ev,pr);
		if(ev.isCancelled())return;
		// execute skill in hand
		SkillInstance weaponSkill = this.getSkillInstance(p2.getItemInHand());
		if (weaponSkill != null)
			weaponSkill.onBeDamageByEntity(ev,pr);
		if(ev.isCancelled())return;
		getHookedSkills(p2).onBeDamageByEntity(ev,pr);
		if(ev.isCancelled())return;
		// execute skill in armor
		for (ItemStack item : p2.getEquipment().getArmorContents()) {
			SkillInstance armorSkill = this.getSkillInstance(item);
			if (armorSkill != null)
				armorSkill.onBeDamageByEntity(ev,pr);
			if(ev.isCancelled())return;
		}
	}
	@EventHandler(priority = EventPriority.LOWEST)
	private void onBeDamaged(EntityDamageEvent ev) {
		//if(ev instanceof EntityDamageByEntityEvent)return;
		executeBeDamaged(ev,SkillPriority.BeforeDispatch);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	private void onAfterBeDamaged(EntityDamageEvent ev) {
		//if(ev instanceof EntityDamageByEntityEvent)return;
		executeBeDamaged(ev,SkillPriority.AfterDispatch);
	}
	private void executeBeDamaged(EntityDamageEvent ev,SkillPriority pr) {
		if (!(ev.getEntity() instanceof Player))
			return;
		Player p = (Player) ev.getEntity();
		PlayerEffects pe = playereffect.get(p);
		if (pe != null)
			pe.onBeDamaged(ev,pr);
		SkillInstance handSkill = this.getSkillInstance(p.getItemInHand());
		if (handSkill != null)
			handSkill.onBeDamaged(ev,pr);
		if(ev.isCancelled())return;
		getHookedSkills(p).onBeDamaged(ev,pr);
		for (ItemStack item : p.getEquipment().getArmorContents()) {
			SkillInstance armorSkill = this.getSkillInstance(item);
			if (armorSkill != null)
				armorSkill.onBeDamaged(ev,pr);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPlayerDied(PlayerDeathEvent ev) {
		Player p = ev.getEntity();
		PlayerEffects pe = playereffect.get(p);
		if (pe != null)
			pe.clear();
	}

	public void defineEffect(String name, EffectFactory eff) {
		effects.put(name, eff);
	}

	public EffectFactory undefineEffect(String name) {
		return effects.remove(name);
	}

	public void defineSkill(String name, Skill sk) {
		skills.put(name, sk);
	}

	public Skill undefineSkill(String name) {
		return skills.remove(name);
	}

	public static SkillEffectManager getManager() {
		return manager;
	}

	public Set<String> getEffectList() {
		return effects.keySet();
	}

	public Set<String> getSkillList() {
		return skills.keySet();
	}
}
