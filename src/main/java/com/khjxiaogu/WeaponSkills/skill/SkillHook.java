package com.khjxiaogu.WeaponSkills.skill;

import java.util.Set;

import org.bukkit.entity.Player;

// TODO: Auto-generated Javadoc
/**
 * Interface SkillHook.
 *
 * @author khjxiaogu
 * file: SkillHook.java
 * time: 2020年9月4日
 */
public interface SkillHook {
	
	/**
	 * Gets the skill.<br>
	 * 获取 skill.
	 *
	 * @param p the p<br>
	 * @return skill<br>
	 */
	public Set<SkillDescription> getSkill(Player p);
	
	/**
	 * Gets the name.<br>
	 * 获取 name.
	 *
	 * @return name<br>
	 */
	public String getName();
}
