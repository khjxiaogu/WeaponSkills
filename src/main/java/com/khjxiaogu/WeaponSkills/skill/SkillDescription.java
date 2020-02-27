package com.khjxiaogu.WeaponSkills.skill;

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
	/**
	 * 为输出使用
	 * for output
	 * @param n skill name
	 * @param s skill instance
	 * @param l level
	 */
	public SkillDescription(String n,Skill s,int l) {
		name=n;
		level=l;
		skill=s;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 为给物品设置技能使用
	 * for setting skill for item
	 * @param n 技能名字 skill name
	 * @param l 技能等级 skill level
	 */
	public SkillDescription(String n,int l) {
		name=n;
		level=l;
		skill=null;
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
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	@Override
	public boolean equals(Object another) {
		if(another==this)return true;
		if(!(another instanceof SkillDescription))return false;
		return name.equals(((SkillDescription)another).name);
	}
}
