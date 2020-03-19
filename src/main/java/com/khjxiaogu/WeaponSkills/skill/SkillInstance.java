package com.khjxiaogu.WeaponSkills.skill;

import java.util.Arrays;
import java.util.Set;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.khjxiaogu.WeaponSkills.SkillEffectManager;

public class SkillInstance {
	Skill[] skills;
	int[] levels;
	public SkillInstance(Skill[] skills,int[] levels) {
		this.skills=skills;
		this.levels=levels;
		// TODO Auto-generated constructor stub
	}
	public SkillInstance(Set<SkillDescription> skillset) {
		int size=skillset.size();
		skills=new Skill[size];
		levels=new int[size];
		if(size==0)
			return;
		int i=0;
		for(SkillDescription skill:skillset) {
			if(skill.skill!=null)
				skills[i]=skill.skill;
			else
				skills[i]=SkillEffectManager.getManager().getSkillByName(skill.name);
			levels[i]=skill.level;
			i++;
		}
		// TODO Auto-generated constructor stub
	}
	public void onDoDamage(EntityDamageByEntityEvent ev,SkillPriority pr) {
		if(skills.length==0)
			return;
		for(int i=0;i<skills.length;i++) {
			if(skills[i]!=null&&skills[i].getExecutionPriority()==pr)
				skills[i].onDoDamage(ev, levels[i]);
			if(ev.isCancelled())break;
		}
	};
	public void onRightClickEntity(PlayerInteractEntityEvent ev) {
		if(skills.length==0)
			return;
		for(int i=0;i<skills.length;i++) {
			if(skills[i]!=null)
				skills[i].onRightClickEntity(ev, levels[i]);
			if(ev.isCancelled())break;
		}
	};
	public void onRightClickBlock(PlayerInteractEvent ev) {
		if(skills.length==0)
			return;
		for(int i=0;i<skills.length;i++) {
			if(skills[i]!=null)
				skills[i].onRightClickBlock(ev, levels[i]);
			if(ev.isCancelled())break;
		}
	};
	public void onBeDamageByEntity(EntityDamageByEntityEvent ev,SkillPriority pr) {
		if(skills.length==0)
			return;
		for(int i=0;i<skills.length;i++) {
			if(skills[i]!=null&&skills[i].getExecutionPriority()==pr)
				skills[i].onBeDamageByEntity(ev, levels[i]);
			if(ev.isCancelled())break;
		}
	};
	public void onBeDamaged(EntityDamageEvent ev,SkillPriority pr) {
		if(skills.length==0)
			return;
		for(int i=0;i<skills.length;i++) {
			if(skills[i]!=null&&skills[i].getExecutionPriority()==pr)
				skills[i].onBeDamaged(ev, levels[i]);
			if(ev.isCancelled())break;
		}
	};
}
