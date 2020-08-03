package com.khjxiaogu.WeaponSkills.effect;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.khjxiaogu.WeaponSkills.skill.SkillPriority;

public interface EffectInstance {
		public String getEffectIdentifier();
		public long getExpireTime();
		public void onDoDamage(EntityDamageByEntityEvent ev);
		public void onBeDamagedByEntity(EntityDamageByEntityEvent ev);
		public void onBeDamaged(EntityDamageEvent ev);
		public void onTick();
		public void onRemove(boolean timeout);
		public SkillPriority getExecutionPriority();
}
