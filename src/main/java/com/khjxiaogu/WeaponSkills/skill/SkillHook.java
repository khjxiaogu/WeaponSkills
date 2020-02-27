package com.khjxiaogu.WeaponSkills.skill;

import java.util.Set;

import org.bukkit.entity.Player;

public interface SkillHook {
	public Set<SkillDescription> getSkill(Player p);
	public String getName();
}
