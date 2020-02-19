package com.khjxiaogu.WeaponSkills;

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
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.khjxiaogu.WeaponSkills.Effects.EffectFactory;
import com.khjxiaogu.WeaponSkills.Effects.PlayerEffects;
import com.khjxiaogu.WeaponSkills.Skills.Skill;
import com.khjxiaogu.WeaponSkills.Skills.SkillDescription;
import com.khjxiaogu.WeaponSkills.Skills.SkillInstance;

import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;

public class SkillEffectManager implements Listener {
	public static SkillEffectManager manager = new SkillEffectManager();
	private Map<String, EffectFactory> effects = new ConcurrentHashMap<>();
	private Map<Player, PlayerEffects> playereffect = new ConcurrentHashMap<>();
	private Map<String, Skill> skills = new ConcurrentHashMap<>();
	private NBTManager nm = NBTManager.getInstance();

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
			WeaponSkills.plugin.getLogger().warning("技能" + name + "不存在！");
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

	public void writeSkill(ItemStack item, String skill, int level) {
		NBTCompound nbt = nm.read(item);
		if (nbt == null)
			nbt = new NBTCompound();
		NBTCompound pnbt = nbt.getCompound("SkillEffects");
		if (pnbt == null)
			pnbt = new NBTCompound();
		pnbt.put("Skill", skill);
		pnbt.put("Skilllevel", level);
		nbt.put("SkillEffects", pnbt);
		nm.write(item, nbt);

	}

	public SkillDescription readSkill(ItemStack item) {
		NBTCompound nbt = nm.read(item);
		if (nbt == null)
			return null;
		NBTCompound pnbt = nbt.getCompound("SkillEffects");
		if (pnbt == null)
			return null;
		String Skills = pnbt.getString("Skill");
		int level = pnbt.getInt("Skilllevel");
		return new SkillDescription(Skills, skills.get(Skills), level);
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
		int level = pnbt.getInt("Skilllevel");
		Skill current = skills.get(Skills);
		if (current == null)
			return null;
		return new SkillInstance(current, level);
	}

	public void removeSkill(ItemStack item) {
		NBTCompound nbt = nm.read(item);
		if (nbt == null)
			nbt = new NBTCompound();
		nbt.remove("SkillEffects");
		nm.write(item, nbt);
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void onInteractEntity(PlayerInteractEntityEvent ev) {
		ItemStack hand = ev.getPlayer().getItemInHand();
		if (hand == null || hand.getType() == Material.AIR)
			return;
		NBTCompound nbt = nm.read(hand);
		if (nbt == null)
			return;
		NBTCompound pnbt = nbt.getCompound("SkillEffects");
		if (pnbt == null)
			return;
		String Skills = pnbt.getString("Skill");
		int level = pnbt.getInt("Skilllevel");
		if (Skills == null)
			return;
		Skill current = skills.get(Skills);
		if (current == null)
			return;
		current.onRightClickEntity(ev, level);
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void onInteractBlock(PlayerInteractEvent ev) {
		ItemStack hand = ev.getPlayer().getItemInHand();
		if (hand == null || hand.getType() == Material.AIR)
			return;
		NBTCompound nbt = nm.read(hand);
		if (nbt == null)
			return;
		NBTCompound pnbt = nbt.getCompound("SkillEffects");
		if (pnbt == null)
			return;
		String Skills = pnbt.getString("Skill");
		int level = pnbt.getInt("Skilllevel");
		Skill current = skills.get(Skills);
		if (current == null)
			return;
		current.onRightClickBlock(ev, level);
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void onDoDamage(EntityDamageByEntityEvent ev) {
		// handle the damager
		if (!(ev.getDamager() instanceof Player))
			return;
		Player p = (Player) ev.getDamager();
		// apply effect
		PlayerEffects pe = playereffect.get(p);
		if (pe != null)
			pe.onDoDamage(ev);
		// execute skill in hand
		SkillInstance handSkill = this.getSkillInstance(p.getItemInHand());
		if (handSkill != null)
			handSkill.onDoDamage(ev);
		// execute skill in armor
		for (ItemStack item : p.getEquipment().getArmorContents()) {
			SkillInstance armorSkill = this.getSkillInstance(item);
			if (armorSkill != null)
				armorSkill.onDoDamage(ev);
		}
		// now handle the damagee
		if (!(ev.getEntity() instanceof Player))
			return;
		Player p2 = (Player) ev.getEntity();
		// apply effect
		PlayerEffects pe2 = playereffect.get(p2);
		if (pe2 != null)
			pe2.onBeDamaged(ev);
		// execute skill in hand
		SkillInstance weaponSkill = this.getSkillInstance(p2.getItemInHand());
		if (weaponSkill != null)
			weaponSkill.onBeDamageByEntity(ev);
		// execute skill in armor
		for (ItemStack item : p2.getEquipment().getArmorContents()) {
			SkillInstance armorSkill = this.getSkillInstance(item);
			if (armorSkill != null)
				armorSkill.onBeDamageByEntity(ev);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void onBeDamaged(EntityDamageEvent ev) {
		if (!(ev.getEntity() instanceof Player))
			return;
		Player p = (Player) ev.getEntity();
		PlayerEffects pe = playereffect.get(p);
		if (pe != null)
			pe.onBeDamaged(ev);
		SkillInstance handSkill = this.getSkillInstance(p.getItemInHand());
		if (handSkill != null)
			handSkill.onBeDamaged(ev);
		for (ItemStack item : p.getEquipment().getArmorContents()) {
			SkillInstance armorSkill = this.getSkillInstance(item);
			if (armorSkill != null)
				armorSkill.onBeDamaged(ev);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
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
