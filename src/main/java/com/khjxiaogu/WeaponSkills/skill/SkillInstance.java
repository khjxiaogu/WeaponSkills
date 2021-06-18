package com.khjxiaogu.WeaponSkills.skill;

import java.util.AbstractList;
import java.util.List;
import java.util.Set;

import com.khjxiaogu.WeaponSkills.SkillEffectManager;
import com.khjxiaogu.WeaponSkills.events.PlayerBeDamageEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerClickEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDamagedEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDoDamageEvent;

public class SkillInstance extends AbstractList<SkillDescription>{
	Skill[] skills;
	int[] levels;

	public SkillInstance(Skill[] skills, int[] levels) {
		this.skills = skills;
		this.levels = levels;
		// TODO Auto-generated constructor stub
	}

	public SkillInstance(Set<SkillDescription> skillset) {
		int size = skillset.size();
		skills = new Skill[size];
		levels = new int[size];
		if (size == 0)
			return;
		int i = 0;
		for (SkillDescription skill : skillset) {
			if (skill.skill != null) {
				skills[i] = skill.skill;
			} else {
				skills[i] = SkillEffectManager.getManager().getSkillByName(skill.name);
			}
			levels[i] = skill.level;
			i++;
		}
		// TODO Auto-generated constructor stub
	}

	public void onDoDamage(PlayerDoDamageEvent ev, SkillPriority pr) {
		if (skills.length == 0)
			return;
		for (int i = 0; i < skills.length; i++) {
			if (skills[i] != null && skills[i].getExecutionPriority() == pr) {
				skills[i].onDoDamage(ev, levels[i]);
			}
			if (ev.isCancelled()) {
				break;
			}
		}
	};

	public void onRightClick(PlayerClickEvent ev) {
		if (skills.length == 0)
			return;
		for (int i = 0; i < skills.length; i++) {
			if (skills[i] != null) {
				skills[i].onRightClick(ev, levels[i]);
			}
			if (ev.isCancelled()) {
				break;
			}
		}
	};

	public void onBeDamageByEntity(PlayerBeDamageEvent ev, SkillPriority pr) {
		if (skills.length == 0)
			return;
		for (int i = 0; i < skills.length; i++) {
			if (skills[i] != null && skills[i].getExecutionPriority() == pr) {
				skills[i].onBeDamageByEntity(ev, levels[i]);
			}
			if (ev.isCancelled()) {
				break;
			}
		}
	};

	public void onBeDamaged(PlayerDamagedEvent ev, SkillPriority pr) {
		if (skills.length == 0)
			return;
		for (int i = 0; i < skills.length; i++) {
			if (skills[i] != null && skills[i].getExecutionPriority() == pr) {
				skills[i].onBeDamaged(ev, levels[i]);
			}
			if (ev.isCancelled()) {
				break;
			}
		}
	}

	@Override
	public SkillDescription get(int i) {
		return new SkillDescription(skills[i].getName(),skills[i],levels[i]);
	}

	@Override
	public int size() {
		return skills.length;
	};
}
