package com.khjxiaogu.WeaponSkills.effect;

import org.bukkit.entity.Player;

import com.khjxiaogu.WeaponSkills.events.PlayerBeDamageEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDamagedEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDoDamageEvent;
import com.khjxiaogu.WeaponSkills.skill.SkillPriority;
import com.khjxiaogu.khjxiaogu.TimeUtil;

public class DataOwnedEffectInstance implements EffectInstance {
	private DataOwnedEffect effect;
	private int time;
	private long expires;

	public DataOwnedEffectInstance(Player p, Class<? extends DataOwnedEffect> e, int time, int level) throws Exception {
		expires = TimeUtil.getTime() + time;
		effect = e.getConstructor().newInstance();
		effect.initEffect(p, time, level);
		// TODO Auto-generated constructor stub
	}

	public int getTime() {
		return time;
	}

	@Override
	public long getExpireTime() {
		return expires;
	}

	@Override
	public void onDoDamage(PlayerDoDamageEvent ev) {
		// TODO Auto-generated method stub
		effect.onDoDamage(ev);
	}

	@Override
	public void onBeDamagedByEntity(PlayerBeDamageEvent ev) {
		// TODO Auto-generated method stub
		effect.onBeDamagedByEntity(ev);
	}

	@Override
	public void onBeDamaged(PlayerDamagedEvent ev) {
		// TODO Auto-generated method stub
		effect.onBeDamaged(ev);
	}

	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		effect.onTick();
	}

	@Override
	public void onRemove(boolean timeout) {
		effect.onEnd(timeout);
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
