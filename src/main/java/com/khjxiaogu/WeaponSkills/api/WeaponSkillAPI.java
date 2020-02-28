package com.khjxiaogu.WeaponSkills.api;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.khjxiaogu.WeaponSkills.CoolDownUtil;
import com.khjxiaogu.WeaponSkills.SkillEffectManager;
import com.khjxiaogu.WeaponSkills.effect.EffectFactory;
import com.khjxiaogu.WeaponSkills.effect.EffectInstance;
import com.khjxiaogu.WeaponSkills.skill.Skill;
import com.khjxiaogu.WeaponSkills.skill.SkillDescription;
import com.khjxiaogu.WeaponSkills.skill.SkillHook;

public class WeaponSkillAPI {
	private SkillEffectManager manager;
	private static WeaponSkillAPI api = new WeaponSkillAPI();

	private WeaponSkillAPI() {
		manager = SkillEffectManager.getManager();
		api = this;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取API对象
	 * get this api object
	 * 
	 * @return 本类实例 instance of this api
	 */
	public static WeaponSkillAPI getAPI() {
		return api;
	}

	/**
	 * 给予玩家指定名字的效果
	 * add a named and registered effect to a player
	 * @param name  效果名称 effect identifier
	 * @param p     玩家 target player
	 * @param time  时间
	 * @param level 等级
	 * @return 成功返回true，效果不存在返回false true if succeed,false if effect not exist
	 */
	public boolean giveEffect(String name, Player p, int time, int level) {
		return manager.giveEffect(p, name, time, level);
	}

	/**
	 * 给予玩家一个任意效果
	 * add an anonymous effect to a player
	 * @param effect effectFactory class
	 * @param p 玩家 target player
	 * @param time 时间
	 * @param level 等级
	 */
	public void giveEffect(EffectFactory effect, Player p, int time, int level) {
		manager.giveEffect(p, effect, time, level);
	}
	public EffectInstance getEffect(Player p,EffectFactory effect) {
		return manager.getEffect(p, effect);
	}
	public EffectInstance getEffect(Player p,String name) {
		return manager.getEffect(p,name);
	}
	public boolean hasEffect(Player p,EffectFactory effect) {
		return manager.hasEffect(p, effect);
	}
	public boolean hasEffect(Player p,String name) {
		return manager.hasEffect(p, name);
	}
	/**
	 * 给一个物品写入一个技能，无需写入SkillDescription的skill项，只需填写name和level即可
	 * writes skills to an item
	 * SkillDescription's skill field is not necessary,for it only check the name and level.
	 * @param item 目标物品 target item
	 * @param skills 技能集合 Set of skills
	 * 
	 */
	public void writeSkills(ItemStack item, Set<SkillDescription> skills) {
		manager.writeSkill(item,skills);
	}

	/**
	 * 读取一个物品的技能
	 * get skills of an item
	 * @param item 要读取的物品 target item
	 * @return 技能描述对象集合，如果物品没有技能则为null Set of SkillDescription object contains the info,null if no skill is presented on this item
	 */
	public Set<SkillDescription> readSkill(ItemStack item) {
		return manager.readSkill(item);
	}

	/**
	 * 移除对应物品的技能
	 * remove all skills on an item
	 * @param item 目标物品 target item
	 */
	public void removeSkill(ItemStack item) {
		manager.removeSkill(item);
	}

	/**
	 * 注册一个效果，并覆盖同名效果
	 * register an effect,overwrite if an effect with an equal identifier exist
	 * @param effect 要注册的效果 effect to register
	 */
	public void registerEffectFactory(EffectFactory effect) {
		manager.defineEffect(effect.getEffectIdentifier(), effect);
	}

	/**
	 * 移除一个效果
	 * removes an effect
	 * @param effect 要移除的效果 effect to remove
	 * @return 被移除了的效果 removed effect
	 */
	public EffectFactory removeEffectFactory(EffectFactory effect) {
		return manager.undefineEffect(effect.getEffectIdentifier());
	}
	/**
	 * 移除对应名字的效果
	 * removes an effect by identifier
	 * @param name 要移除的效果名 identifier of the effect to remove
	 * @return 被移除了的效果 removed effect
	 */
	public EffectFactory removeEffect(String name) {
		return manager.undefineEffect(name);
	}
	/**
	 * 注册一个技能
	 * register a skill,overwrite if a skill with an equal name exist
	 * @param skill 要注册的技能 skill to register 
	 */
	public void registerSkill(Skill skill) {
		manager.defineSkill(skill.getName(), skill);
	}

	/**
	 * 移除一个技能
	 * remove a skill
	 * @param skill 要移除的技能 skill to remove
	 * @return 移除了的技能 removed skill
	 */
	public Skill removeSkill(Skill skill) {
		return manager.undefineSkill(skill.getName());
	}

	/**
	 * 移除对应名字的技能
	 * remove a skill by name
	 * @param name 技能名称 name of the skill to remove
	 * @return 移除了的技能 removed skill
	 */
	public Skill removeSkill(String name) {
		return manager.undefineSkill(name);
	}
	public Skill getSkill(String name) {
		return manager.getSkillByName(name);
	}
	public EffectFactory getEffect(String name) {
		return manager.getEffectByName(name);
	}
	public void RegisterSkillHook(SkillHook hook) {
		manager.RegisterSkillHook(hook.getName(), hook);
	}
	public void RegisterSkillHook(String name,SkillHook hook) {
		manager.RegisterSkillHook(name, hook);
	}
	public void UnRegisterSkillHook(SkillHook hook) {
		manager.UnregisterSkillHook(hook.getName());
	}
	public void UnRegisterSkillHook(String name) {
		manager.UnregisterSkillHook(name);
	}
	/**
	 * 获取效果m名称列表 get all registered effects
	 * @return a set of identifier of registered effects
	 */
	public Set<String> getEffectList() {
		return manager.getEffectList();
	}

	/**
	 *  获取效果m名称列表 get all registered skills
	 * @return a set of names of registered skills
	 */
	public Set<String> getSkillList() {
		return manager.getSkillList();
	}
	
	/**
	 * 为玩家启用一个冷却，需要自行判断是否在冷却时间内。如果玩家在冷却时间内，不会重新设置冷却
	 * set a cool down for a player,you should check the result every time the skill triggered.
	 * this would not refresh cool down if player is in cool down.
	 * @param p 目标玩家 target player
	 * @param skill 冷却的技能 skill to cool down
	 * @param milliSecond 冷却时间ms cool down time in milliseconds
	 * @return 如果玩家不在冷却时间内，返回0，如果玩家正在冷却中，返回剩余时间ms return 0 if target player is not in cool down,otherwise return time rest
	 */
	public long applyCoolDown(Player p, Skill skill, int milliSecond) {
		return CoolDownUtil.applyCoolDown(p, skill.getName(), milliSecond);
	}

	/**
	 * 检测一个玩家是否在冷却时间内
	 * check if a player is in cool down
	 * @param p 目标玩家 target player
	 * @param skill 技能 target skill
	 * @return 玩家是否在冷却时间内 if a player is in cool down.
	 */
	public boolean isCoolDown(Player p, Skill skill) {
		return CoolDownUtil.isCoolDown(p, skill.getName());
	}

	/**
	 * 重置一个技能的冷却
	 * reset cool down of the skill
	 * @param p 玩家 target player
	 * @param skill 技能 target skill
	 */
	public void resetCoolDown(Player p, Skill skill) {
		CoolDownUtil.resetCoolDown(p, skill.getName());
	}

	/**
	 * 更新冷却时间并覆盖旧冷却时间
	 * renew the cool down and overwrite the old one
	 * @param p 目标玩家 target player
	 * @param skill 技能 skill to renew.
	 * @param milliSecond 冷却时间 new cool down time
	 */
	public void renewCoolDown(Player p, Skill skill, int milliSecond) {
		CoolDownUtil.renewCoolDown(p, skill.getName(), milliSecond);
	}
}
