package com.khjxiaogu.WeaponSkills.Skills;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SkillInstance {
	Skill skill;
	int level;
	public SkillInstance(Skill skill,int level) {
		this.skill=skill;
		this.level=level;
		// TODO Auto-generated constructor stub
	}
	public void onDoDamage(EntityDamageByEntityEvent ev) {
		skill.onDoDamage(ev, level);
	};
	public void onRightClickEntity(PlayerInteractEntityEvent ev) {
		skill.onRightClickEntity(ev, level);
	};
	public void onRightClickBlock(PlayerInteractEvent ev) {
		skill.onRightClickBlock(ev, level);
	};
	public void onBeDamageByEntity(EntityDamageByEntityEvent ev) {
		skill.onBeDamageByEntity(ev, level);
	};
	public void onBeDamaged(EntityDamageEvent ev) {
		skill.onBeDamaged(ev, level);
	};
}
