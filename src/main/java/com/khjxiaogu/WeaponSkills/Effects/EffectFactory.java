package com.khjxiaogu.WeaponSkills.Effects;

import org.bukkit.entity.Player;

public interface EffectFactory {
	public EffectInstance createEffectInstance(Player p, int time, int level);
	public String getEffectIdentifier();
}
