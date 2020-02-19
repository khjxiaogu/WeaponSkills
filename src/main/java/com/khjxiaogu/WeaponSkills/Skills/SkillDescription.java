package com.khjxiaogu.WeaponSkills.Skills;

/**
 * 用于描述物品技能的类
 * class to describe skill
 * @author khjxiaogu
 * @time 2020年2月18日
 * file:SkillDescription.java
 */
public class SkillDescription {
	String name;
	Skill skill;
	int level;
	public SkillDescription(String n,Skill s,int l) {
		name=n;
		level=l;
		skill=s;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获得名字
	 * get name of skill
	 * @return 技能名字 skill name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 获得技能等级
	 * get level of skill
	 * @return 技能等级 skill level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * 获得技能类
	 * get class of skill
	 * @return 技能类对象 class of skill,null if skill not exist.
	 */
	public Skill getSkill() {
		return skill;
	}
}
