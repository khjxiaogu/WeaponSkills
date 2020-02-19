package com.khjxiaogu.WeaponSkills;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.khjxiaogu.WeaponSkills.Skills.SkillDescription;
import com.khjxiaogu.WeaponSkills.api.SkillRegister;
import com.khjxiaogu.khjxiaogu.TimeUtil;

public class WeaponSkills extends JavaPlugin implements CommandExecutor,Listener {
	public static WeaponSkills plugin;
	private static SkillEffectManager manager;
	@Override
	public void onEnable() {
		plugin=this;
		manager=SkillEffectManager.getManager();
		SkillRegister.registAll();
    	TimeUtil.setPlugin(this);
    	Bukkit.getPluginManager().registerEvents(manager,this);
	}
	@Override
	public boolean onCommand(CommandSender sender,Command command, String s, String[] strings) {
		if(strings.length==0){
			sender.sendMessage("§e武器技能插件插件  By khjxiaogu");
			sender.sendMessage("§e输入/wskill set [技能名称] [技能等级|0] 设置手中物品技能，等级为0即移除");
			sender.sendMessage("§e输入/wskill read 读取手中物品技能");
			sender.sendMessage("§e输入/wskill effect <玩家> <效果名称> <效果等级> <效果持续时间ms> 给玩家一个插件效果");
			sender.sendMessage("§e输入/wskill list <skill/effect> 查看技能/效果列表");
			return true;
		}else if(sender instanceof Player) {
			Player p=(Player) sender;
			if(strings[0].equals("set")) {
				ItemStack is=p.getItemInHand();
				if(strings.length<2) {
					manager.removeSkill(is);
					p.setItemInHand(is);
					p.sendMessage("§e技能已经清除！");
				}else if(strings.length==2) {
					manager.writeSkill(is,strings[1],1);
					p.setItemInHand(is);
					p.sendMessage("§e技能已经写入！");
				}else {
					int lvl=Integer.parseInt(strings[2]);
					if(lvl>0) {
						manager.writeSkill(is,strings[1],lvl);
						p.setItemInHand(is);
						p.sendMessage("§e技能已经写入！");
					}else {
						manager.removeSkill(is);
						p.setItemInHand(is);
						p.sendMessage("§e技能已经清除！");
					}
				}
				return true;
			}else if(strings[0].equals("read")) {
				ItemStack is=p.getItemInHand();
				SkillDescription skill=manager.readSkill(is);
				if(skill==null) {
					p.sendMessage("§4该物品查无信息！");
					return true;
				}
				p.sendMessage("§e手中物品技能为 §3"+skill.getName()+"[lv."+skill.getLevel()+(skill.getSkill()==null?"] §4技能无效！":"] §a技能有效！"));
				return true;
			}else if(strings[0].equals("effect")) {
				if(strings.length>=5) {
					if(manager.giveEffect(Bukkit.getPlayer(strings[1]),strings[2],Integer.parseInt(strings[4]),Integer.parseInt(strings[3]))) {
						p.sendMessage("§e效果给予成功！");
					}else {
						p.sendMessage("§4效果不存在！");
					}
					return true;
				}else {
					p.sendMessage("§4参数不足");
					return false;
				}
			}
		}else if(strings[0].equals("list")) {
			if(strings.length>1) {
				if(strings[1].equals("effect")) {
					sender.sendMessage("§e效果列表：");
					for(String name:manager.getEffectList()) {
						sender.sendMessage("§e  "+name);
					}
				}else if(strings[1].equals("skill")){
					sender.sendMessage("§e技能列表：");
					for(String name:manager.getSkillList()) {
						sender.sendMessage("§e  "+name);
					}
				}else {
					sender.sendMessage("§4参数有误");
					return false;
				}
			}else {
				sender.sendMessage("§4参数不足");
				return false;
			}
			return true;
		}
		return false;
	}
}
