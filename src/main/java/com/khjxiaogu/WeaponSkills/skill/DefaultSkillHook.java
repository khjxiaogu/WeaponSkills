package com.khjxiaogu.WeaponSkills.skill;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.khjxiaogu.WeaponSkills.WeaponSkills;
import com.khjxiaogu.WeaponSkills.api.WeaponSkillAPI;

// TODO: Auto-generated Javadoc
/**
 * Class DefaultSkillHook.
 *
 * @author khjxiaogu
 *         file: DefaultSkillHook.java
 *         time: 2020年9月4日
 */
public class DefaultSkillHook implements SkillHook {

	/**
	 * The skills.<br>
	 * 成员 skills.
	 */
	Map<String, Set<SkillDescription>> skills = new ConcurrentHashMap<>();
	protected final String plugin;

	/**
	 * Instantiates a new DefaultSkillHook with plugin name.<br>
	 * 使用插件名新建一个DefaultSkillHook类<br>
	 *
	 * @param name the plugin name<br>
	 *             插件名称
	 */
	public DefaultSkillHook(String name) {
		plugin = name;
		WeaponSkillAPI.getAPI().RegisterSkillHook(this);
	}

	@Override
	public Set<SkillDescription> getSkill(Player p) {
		Set<SkillDescription> sds = skills.get(p.getName());
		if (sds == null) {
			sds = Collections.newSetFromMap(new ConcurrentHashMap<>());
			skills.put(p.getName(), sds);
		}
		return sds;
	}

	/**
	 * add a skill for a player.<br>
	 * 为玩家添加技能
	 * 
	 * @param p     the player<br>
	 *              对应玩家
	 * @param skill skill name<br>
	 *              技能名称
	 * @param level skill level<br>
	 *              技能等级
	 */
	public void addSkillFor(Player p, String skill, int level) {
		WeaponSkills.plugin.getLogger().info("plugin "+plugin+" applied for a skill addon of "+skill+"[lv."+level+"] for "+p);
		Set<SkillDescription> sd = getSkill(p);
		for (SkillDescription s : sd) {
			if (s.getName().equals(skill)) {
				s.level = level;
				return;
			}
		}
		sd.add(new SkillDescription(skill, level));
	}

	/**
	 * Sets the skills for a player.<br>
	 * 设置一个玩家的所有技能
	 * 
	 * @param p      the player<br>
	 *               对应玩家
	 * @param skills the skills<br>
	 *               技能
	 */
	public void setSkillsFor(Player p, SkillDescription... skills) {
		Set<SkillDescription> sd = getSkill(p);
		sd.clear();
		sd.addAll(Arrays.asList(skills));
	}

	/**
	 * Clear skills for a player.<br>
	 * 清除一个玩家的技能
	 * 
	 * @param p the p<br>
	 *          玩家
	 */
	public void clearSkillsFor(Player p) {
		WeaponSkills.plugin.getLogger().info("plugin "+plugin+" applied for a skill reset for "+p);
		Set<SkillDescription> sd = getSkill(p);
		sd.clear();
	}

	/**
	 * Gets the name.<br>
	 * 获取 name.
	 *
	 * @return name<br>
	 */
	@Override
	public String getName() {
		return plugin;
	}

}
