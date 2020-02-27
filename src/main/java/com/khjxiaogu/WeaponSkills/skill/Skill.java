 package com.khjxiaogu.WeaponSkills.skill;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Skill {
	/**
	 * 有此技能的玩家攻击时呼叫
	 * called when a player with this skill attacks others
	 * @param ev 攻击事件 attack event
	 * @param level 等级
	 */
	public void onDoDamage(EntityDamageByEntityEvent ev,int level);
	/**
	 * 有此技能的玩家右键实体时呼叫
	 * called when a player with this skill interact an entity
	 * @param ev 交互事件 interact event
	 * @param level 等级
	 */
	public void onRightClickEntity(PlayerInteractEntityEvent ev,int level);
	/**
	 * 有此技能的玩家右键方块时呼叫
	 * called when a player with this skill interact a block
	 * @param ev 交互事件 interact event
	 * @param level 等级
	 */
	public void onRightClickBlock(PlayerInteractEvent ev,int level);
	/**
	 *  有此技能的玩家遭到实体攻击时呼叫
	 *  called when a player with this skill attacked by an entity.
	 * @param ev 受击事件 attack event
	 * @param level 等级
	 */
	public void onBeDamageByEntity(EntityDamageByEntityEvent ev,int level);
	/**
	 * 有此技能的玩家受到伤害时呼叫，被实体攻击时也会呼叫。
	 * called when a player with this skill being damaged,would also called if damaged by an entity.
	 * @param ev 受伤事件 damage event
	 * @param level 等级
	 */
	public void onBeDamaged(EntityDamageEvent ev,int level);
	/**
	 * 获取该技能的名字
	 * returns name of this skill,must be unique
	 * @return 效果名字 name of this skill
	 */
	public String getName();
	/**
	 * 获取该技能的优先级，默认0
	 * returns name of this skill,must be unique
	 * @return 效果名字 name of this skill
	 */
	public int getPriority();
}