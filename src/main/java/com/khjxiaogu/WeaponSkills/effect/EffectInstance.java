package com.khjxiaogu.WeaponSkills.effect;

import com.khjxiaogu.WeaponSkills.events.PlayerBeDamageEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDamagedEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDoDamageEvent;
import com.khjxiaogu.WeaponSkills.skill.SkillPriority;

public interface EffectInstance {
	public String getEffectIdentifier();

	public long getExpireTime();

	public void onDoDamage(PlayerDoDamageEvent ev);

	public void onBeDamagedByEntity(PlayerBeDamageEvent ev);

	public void onBeDamaged(PlayerDamagedEvent ev);

	public void onTick();

	public void onRemove(boolean timeout);

	public SkillPriority getExecutionPriority();
}
