package com.khjxiaogu.WeaponSkills.Effects;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.khjxiaogu.khjxiaogu.TimeUtil;

/**
 * 简单技能的实例，用于储存技能信息
 * instance of simple effects,for store effect details.
 * @author khjxiaogu
 * @time 2020年2月18日
 * file:SimpleEffectInstance.java
 */
public class SimpleEffectInstance implements EffectInstance{
	private SimpleEffect effect;
	private int time;
	private long expires;
	private int level;
	private Player owner;
	public SimpleEffectInstance(Player p,SimpleEffect e,int time,int level) {
		expires=TimeUtil.getTime()+time;
		effect=e;
		owner=p;
		this.level=level;
		effect.onEffectGiven(p, time, level);
		// TODO Auto-generated constructor stub
	}
	public int getTime() {
		return time;
	}
	public int getLevel() {
		return level;
	}
	public SimpleEffect getEffect() {
		return effect;
	}
	public long getExpireTime() {
		return expires;
	}
	public void onDoDamage(EntityDamageByEntityEvent ev) {
		// TODO Auto-generated method stub
		effect.onDoDamage(ev, level);
	}
	@Override
	public void onBeDamagedByEntity(EntityDamageByEntityEvent ev) {
		// TODO Auto-generated method stub
		effect.onBeDamagedByEntity(ev, level);
	}
	public void onBeDamaged(EntityDamageEvent ev) {
		// TODO Auto-generated method stub
		effect.onBeDamaged(ev, level);
	}
	public void onTick() {
		// TODO Auto-generated method stub
		effect.onTick(owner, level);
	}
	public void onRemove() {
		effect.onEnd(owner, level);
	}
	@Override
	public String getEffectIdentifier() {
		// TODO Auto-generated method stub
		return effect.getEffectIdentifier();
	}
}
