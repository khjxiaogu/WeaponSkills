package com.khjxiaogu.WeaponSkills;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.khjxiaogu.WeaponSkills.localization.*;
import com.khjxiaogu.WeaponSkills.skill.SkillDescription;
import com.khjxiaogu.khjxiaogu.KHJUtils;
import com.khjxiaogu.khjxiaogu.TimeUtil;

public class WeaponSkills extends JavaPlugin implements CommandExecutor,Listener {
	public static WeaponSkills plugin;
	private static SkillEffectManager manager;
	LocalizationProvider locale;
	@Override
	public void onEnable() {
		plugin=this;
		manager=SkillEffectManager.getManager();
		this.saveDefaultConfig();
		switch(this.getConfig().getString("lang").toLowerCase()) {
		case "cn":locale=new CN();break;
		case "en":locale=new EN();break;
		}
		KHJUtils.SendPluginInfo(WeaponSkills.plugin);
    	TimeUtil.setPlugin(this);
    	Bukkit.getPluginManager().registerEvents(manager,this);
	}
	@Override
	public boolean onCommand(CommandSender sender,Command command, String s, String[] strings) {
		if(strings.length==0){
			sender.sendMessage(locale.getHelp());
			return true;
		}
		if(sender instanceof Player) {
			Player p=(Player) sender;
			if(strings[0].equals("set")) {
				ItemStack is=p.getItemInHand();
				if(strings.length<2) {
					manager.removeSkill(is);
					p.setItemInHand(is);
					p.sendMessage(locale.getSkillCleared());
				}else if(strings.length==2) {
					Set<SkillDescription> descs=manager.readSkill(is);
					SkillDescription current=new SkillDescription(strings[1],1);
					descs.remove(current);
					descs.add(current);
					manager.writeSkill(is,descs);
					p.setItemInHand(is);
					p.sendMessage(locale.getSkillWritten());
				}else {
					int lvl=Integer.parseInt(strings[2]);
					Set<SkillDescription> descs=manager.readSkill(is);
					if(lvl>0) {
						SkillDescription current=new SkillDescription(strings[1],lvl);
						descs.remove(current);
						descs.add(current);
						manager.writeSkill(is,descs);
						p.setItemInHand(is);
						p.sendMessage(locale.getSkillWritten());
					}else {
						SkillDescription current=new SkillDescription(strings[1],0);
						descs.remove(current);
						manager.writeSkill(is,descs);
						p.setItemInHand(is);
						p.sendMessage(locale.getSkillRemoved());
					}
				}
				return true;
			}else if(strings[0].equals("clear")) {
				ItemStack is=p.getItemInHand();
				manager.removeSkill(is);
				p.sendMessage(locale.getSkillCleared());
				return true;
			}else if(strings[0].equals("read")) {
				ItemStack is=p.getItemInHand();
				Set<SkillDescription> skills=manager.readSkill(is);
				if(skills==null) {
					p.sendMessage(locale.getNoSkill());
					return true;
				}
				p.sendMessage(locale.getSkillInHand());
				for(SkillDescription skill:skills)
					p.sendMessage(" §3"+skill.getName()+"[lv."+skill.getLevel()+"] "+(skill.getSkill()==null? locale.getInvalidSkill():locale.getIsvalidSkill()));
				return true;
			}else if(strings[0].equals("effect")) {
				if(strings.length>=5) {
					if(manager.giveEffect(Bukkit.getPlayer(strings[1]),strings[2],Integer.parseInt(strings[4]),Integer.parseInt(strings[3]))) {
						p.sendMessage(locale.getEffectGiven());
					}else {
						p.sendMessage(locale.getEffectInvalid());
					}
					return true;
				}
					p.sendMessage(locale.getBadParamCount());
					return false;
			}
		}
		if(strings[0].equals("list")) {
			if(strings.length>1) {
				if(strings[1].equals("effect")) {
					sender.sendMessage(locale.getListOfEffect());
					for(String name:manager.getEffectList()) {
						sender.sendMessage("§e  "+name);
					}
				}else if(strings[1].equals("skill")){
					sender.sendMessage(locale.getListOfSkill());
					for(String name:manager.getSkillList()) {
						sender.sendMessage("§e  "+name);
					}
				}else {
					sender.sendMessage(locale.getInvalidParam());
					return false;
				}
			}else {
				sender.sendMessage(locale.getBadParamCount());
				return false;
			}
			return true;
		}
		return false;
	}
}
