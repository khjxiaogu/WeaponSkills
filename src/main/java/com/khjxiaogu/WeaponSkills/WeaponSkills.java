package com.khjxiaogu.WeaponSkills;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.khjxiaogu.WeaponSkills.effect.PlayerEffects;
import com.khjxiaogu.WeaponSkills.localization.CN;
import com.khjxiaogu.WeaponSkills.localization.EN;
import com.khjxiaogu.WeaponSkills.localization.LocalizationProvider;
import com.khjxiaogu.WeaponSkills.skill.SkillDescription;
import com.khjxiaogu.WeaponSkills.skill.SkillInstance;
import com.khjxiaogu.khjxiaogu.KHJUtils;
import com.khjxiaogu.khjxiaogu.TimeUtil;

public class WeaponSkills extends JavaPlugin implements CommandExecutor, Listener {
	public static WeaponSkills plugin;
	private static SkillEffectManager manager;
	LocalizationProvider locale;

	@Override
	public void onEnable() {
		WeaponSkills.plugin = this;
		WeaponSkills.manager = SkillEffectManager.getManager();
		saveDefaultConfig();
		switch (getConfig().getString("lang").toLowerCase()) {
		case "cn":
			locale = new CN();
			break;
		case "en":
			locale = new EN();
			break;
		}
		KHJUtils.SendPluginInfo(WeaponSkills.plugin);
		TimeUtil.setPlugin(this);
		Bukkit.getPluginManager().registerEvents(WeaponSkills.manager, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
		if (strings.length == 0) {
			sender.sendMessage(locale.getHelp());
			return true;
		}
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (strings[0].equals("set")) {
				ItemStack is = p.getItemInHand();
				if (strings.length < 2) {
					WeaponSkills.manager.removeSkill(is);
					p.setItemInHand(is);
					p.sendMessage(locale.getSkillCleared());
				} else if (strings.length == 2) {
					Set<SkillDescription> descs = WeaponSkills.manager.readSkill(is);
					SkillDescription current = new SkillDescription(strings[1], 1);
					descs.remove(current);
					descs.add(current);
					WeaponSkills.manager.writeSkill(is, descs);
					p.setItemInHand(is);
					p.sendMessage(locale.getSkillWritten());
				} else {
					Set<SkillDescription> descs = WeaponSkills.manager.readSkill(is);
					if (strings[2].equals("clear")) {
						SkillDescription current = new SkillDescription(strings[1], 0);
						descs.remove(current);
						WeaponSkills.manager.writeSkill(is, descs);
						p.setItemInHand(is);
						p.sendMessage(locale.getSkillRemoved());
					} else {
						try {
							int lvl = Integer.parseInt(strings[2]);
							SkillDescription current = new SkillDescription(strings[1], lvl);
							descs.remove(current);
							descs.add(current);
							WeaponSkills.manager.writeSkill(is, descs);
							p.setItemInHand(is);
							p.sendMessage(locale.getSkillWritten());
						} catch (Throwable t) {
							p.sendMessage(locale.getInvalidParam());
						}
					}
				}
				return true;
			} else if (strings[0].equals("clear")) {
				ItemStack is = p.getItemInHand();
				WeaponSkills.manager.removeSkill(is);
				p.sendMessage(locale.getSkillCleared());
				return true;
			} else if (strings[0].equals("read")) {
				ItemStack is = p.getItemInHand();
				Set<SkillDescription> skills = WeaponSkills.manager.readSkill(is);
				if (skills == null) {
					p.sendMessage(locale.getNoSkill());
					return true;
				}
				p.sendMessage(locale.getSkillInHand());
				for (SkillDescription skill : skills) {
					p.sendMessage(" §3" + skill.getName() + "[lv." + skill.getLevel() + "] "
							+ (skill.getSkill() == null ? locale.getInvalidSkill() : locale.getIsvalidSkill()));
				}
				return true;
			} else if (strings[0].equals("current")) {
				// execute skill in hand
				SkillInstance handSkill = WeaponSkills.manager.getSkillInstance(p.getItemInHand());
				p.sendMessage(locale.getCurrentListOfSkill());
				if(handSkill!=null){
					p.sendMessage("§e手中物品提供的技能：");
					for (SkillDescription skill : handSkill) {
						p.sendMessage(" §3" + skill.getName() + "[lv." + skill.getLevel() + "] "
								+ (skill.getSkill() == null ? locale.getInvalidSkill() : locale.getIsvalidSkill()));
					}
				}
				SkillInstance hooked=WeaponSkills.manager.getHookedSkills(p);
				if(hooked!=null&&hooked.size()>0) {
					p.sendMessage("§e插件提供的技能：");
					for (SkillDescription skill : hooked) {
						p.sendMessage(" §3" + skill.getName() + "[lv." + skill.getLevel() + "] "
								+ (skill.getSkill() == null ? locale.getInvalidSkill() : locale.getIsvalidSkill()));
					}
				}
				// execute skill in armor
				Set<SkillDescription> skills = new HashSet<>();
				for (ItemStack item : p.getEquipment().getArmorContents()) {
					SkillInstance armorSkill =WeaponSkills.manager.getSkillInstance(item);
					if(armorSkill!=null)
						skills.addAll(armorSkill);
				}
				if(skills.size()>0) {
					p.sendMessage("§e装备提供的技能：");
					for (SkillDescription skill : skills) {
						p.sendMessage(" §3" + skill.getName() + "[lv." + skill.getLevel() + "] "
								+ (skill.getSkill() == null ? locale.getInvalidSkill() : locale.getIsvalidSkill()));
					}
				}
				return true;
			} else if (strings[0].equals("effect")) {
				if (strings.length >= 5) {
					if (WeaponSkills.manager.giveEffect(Bukkit.getPlayer(strings[1]), strings[2],
							Integer.parseInt(strings[4]), Integer.parseInt(strings[3]))) {
						p.sendMessage(locale.getEffectGiven());
					} else {
						p.sendMessage(locale.getEffectInvalid());
					}
					return true;
				}
				p.sendMessage(locale.getBadParamCount());
				return false;
			}
		}
		if (strings[0].equals("list")) {
			if (strings.length > 1) {
				if (strings[1].equals("effect")) {
					sender.sendMessage(locale.getListOfEffect());
					for (String name : WeaponSkills.manager.getEffectList()) {
						sender.sendMessage("§e  " + name);
					}
				} else if (strings[1].equals("skill")) {
					sender.sendMessage(locale.getListOfSkill());
					for (String name : WeaponSkills.manager.getSkillList()) {
						sender.sendMessage("§e  " + name);
					}
				} else {
					sender.sendMessage(locale.getInvalidParam());
					return false;
				}
			} else {
				sender.sendMessage(locale.getBadParamCount());
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] strings) {
		List<String> lstr = new ArrayList<>();
		if (strings.length == 1) {
			lstr.add("set");
			lstr.add("clear");
			lstr.add("read");
			lstr.add("effect");
			lstr.add("list");
			return lstr;
		}
		if (strings.length == 2) {
			if (strings[0].equals("set")) {
				lstr.addAll(WeaponSkills.manager.getSkillList());
				return lstr;
			} else if (strings[0].equals("effect"))
				return super.onTabComplete(sender, command, alias, strings);
			if (strings[0].equals("list")) {
				lstr.add("effect");
				lstr.add("skill");
				return lstr;
			}
		}
		if (strings.length == 3) {
			if (strings[0].equals("effect")) {
				lstr.addAll(WeaponSkills.manager.getEffectList());
				return lstr;
			}
		}
		return lstr;
	}

}
