package com.khjxiaogu.WeaponSkills.effect;

import org.bukkit.entity.Player;

import com.khjxiaogu.WeaponSkills.events.PlayerBeDamageEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDamagedEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDoDamageEvent;
import com.khjxiaogu.WeaponSkills.skill.SkillPriority;

// TODO: Auto-generated Javadoc
/**
 * Interface DataOwnedEffect.
 * Effects that owns data field
 * 
 * @author khjxiaogu
 *         file: DataOwnedEffect.java
 *         time: 2020年8月4日
 */
public interface DataOwnedEffect {

	/**
	 * 添加效果时呼叫此函数
	 * called when this effect is given to the player.
	 *
	 * @param p     目标玩家 target player
	 * @param time  时间
	 * @param level 等级
	 */
	public void initEffect(Player p, int time, int level);

	/**
	 * 有此效果的玩家攻击时呼叫
	 * called when a player with this effect attacks others.
	 *
	 * @param ev 攻击事件 attack event
	 */
	public void onDoDamage(PlayerDoDamageEvent ev);

	/**
	 * 有此效果的玩家遭到实体攻击时呼叫
	 * called when a player with this effect attacked by an entity.
	 * 
	 * @param ev 受击事件 attack event
	 */
	public void onBeDamagedByEntity(PlayerBeDamageEvent ev);

	/**
	 * 有此效果的玩家受到伤害时呼叫，被实体攻击时也会呼叫。
	 * called when a player with this effect being damaged,would also called if
	 * damaged by an entity.
	 * 
	 * @param ev 受伤事件 damage event
	 */
	public void onBeDamaged(PlayerDamagedEvent ev);

	/**
	 * 每5tick呼叫一次，此呼叫为非同步呼叫，如果需要操作，请用BukkitRunnable.runTask并进行操作
	 * called asynchronously per 5 ticks.if any synchronous operation is
	 * needed,please schedule a synchronous event.
	 */
	public void onTick();

	/**
	 * 效果失效时呼叫
	 * called when this effect expires .
	 *
	 * @param timeout true if it is removed by time out 是否因为超时被移除
	 */
	public void onEnd(boolean timeout);

	/**
	 * 获取该效果的名字
	 * returns identifier of this effect,must be unique.
	 *
	 * @return 效果名字 identifier of the effect
	 */
	public String getEffectIdentifier();

	/**
	 * 获取该技能的效果事件执行时间.设置为0则不执行
	 * returns the event execution time in bukkit of damage events,returns null
	 * indicates that this effect has no damage events
	 * 
	 * @return 执行顺序/execution order
	 */
	public SkillPriority getExecutionPriority();
}
