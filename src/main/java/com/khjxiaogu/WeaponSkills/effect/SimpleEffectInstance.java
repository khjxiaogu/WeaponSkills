package com.khjxiaogu.WeaponSkills.effect;

import org.bukkit.entity.Player;

import com.khjxiaogu.WeaponSkills.events.PlayerBeDamageEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDamagedEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDoDamageEvent;
import com.khjxiaogu.WeaponSkills.skill.SkillPriority;
import com.khjxiaogu.khjxiaogu.TimeUtil;

/**
 * 简单技能的实例，用于储存技能信息
 * instance of simple effects,for store effect details.
 * 
 * @author khjxiaogu
 * @time 2020年2月18日
 *       file:SimpleEffectInstance.java
 */
public class SimpleEffectInstance implements EffectInstance {
	private SimpleEffect effect;
	private long expires;
	private int level;
	private Player owner;

	public SimpleEffectInstance(Player p, SimpleEffect e, int time, int level) {
		expires = TimeUtil.getTime() + time;
		effect = e;
		owner = p;
		this.level = level;
		effect.onEffectGiven(p, time, level);
		// TODO Auto-generated constructor stub
	}

	public SimpleEffect getEffect() {
		return effect;
	}

	@Override
	public long getExpireTime() {
		return expires;
	}

	@Override
	public void onDoDamage(PlayerDoDamageEvent ev) {
		// TODO Auto-generated method stub
		effect.onDoDamage(ev, level);
	}

	@Override
	public void onBeDamagedByEntity(PlayerBeDamageEvent ev) {
		// TODO Auto-generated method stub
		effect.onBeDamagedByEntity(ev, level);
	}

	@Override
	public void onBeDamaged(PlayerDamagedEvent ev) {
		// TODO Auto-generated method stub
		effect.onBeDamaged(ev, level);
	}

	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		effect.onTick(owner, level);
	}

	@Override
	public void onRemove(boolean timeout) {
		effect.onEnd(owner, timeout, level);
	}

	@Override
	public String getEffectIdentifier() {
		// TODO Auto-generated method stub
		return effect.getEffectIdentifier();
	}

	@Override
	public SkillPriority getExecutionPriority() {
		return effect.getExecutionPriority();
	}
}
