package com.khjxiaogu.WeaponSkills.Effects;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * A simple effect with static methods. 
 * @author khjxiaogu
 * @time 2020年2月18日
 * file:SimpleEffect.java
 */
public interface SimpleEffect {
	/**
	 * 添加效果时呼叫此函数
	 * called when this effect is given to the player
	 * @param p 目标玩家 target player
	 * @param time 时间
	 * @param level 等级
	 */
	public void onEffectGiven(Player p,int time,int level) ;
	/**
	 * 有此效果的玩家攻击时呼叫
	 * called when a player with this effect attacks others
	 * @param ev 攻击事件 attack event
	 * @param level 等级
	 */
	public void onDoDamage(EntityDamageByEntityEvent ev,int level);
	/**
	 *  有此效果的玩家遭到实体攻击时呼叫
	 *  called when a player with this effect attacked by an entity.
	 * @param ev 受击事件 attack event
	 * @param level 等级
	 */
	public void onBeDamagedByEntity(EntityDamageByEntityEvent ev,int level);
	/**
	 * 有此效果的玩家受到伤害时呼叫，被实体攻击时也会呼叫。
	 * called when a player with this effect being damaged,would also called if damaged by an entity.
	 * @param ev 受伤事件 damage event
	 * @param level 等级
	 */
	public void onBeDamaged(EntityDamageEvent ev,int level);
	/**
	 * 每5tick呼叫一次，此呼叫为非同步呼叫，如果需要操作，请用BukkitRunnable.runTask并进行操作
	 * called asynchronously per 5 ticks.if any synchronous operation is needed,please schedule a synchronous event.
	 * @param p 玩家 player with effect 
	 * @param level 等级
	 */
	public void onTick(Player p,int level);
	/**
	 * 效果失效时呼叫
	 * called when this effect expires 
	 * @param p 玩家 player to remove effect
	 * @param level 等级
	 */
	public void onEnd(Player p,int level);
	/**
	 * 获取该效果的名字
	 * returns identifier of this effect,must be unique
	 * @return 效果名字 identifier of the effect
	 */
	public String getEffectIdentifier();
}
