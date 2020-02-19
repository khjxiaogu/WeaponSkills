package com.khjxiaogu.WeaponSkills.Effects;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public interface EffectInstance {
		public int getTime();
		public int getLevel();
		public String getEffectIdentifier();
		public long getExpireTime();
		public void onDoDamage(EntityDamageByEntityEvent ev);
		public void onBeDamagedByEntity(EntityDamageByEntityEvent ev);
		public void onBeDamaged(EntityDamageEvent ev);
		public void onTick();
		public void onRemove();
}
