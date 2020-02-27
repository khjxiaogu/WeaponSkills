package com.khjxiaogu.WeaponSkills.effect;

import org.bukkit.entity.Player;

import com.khjxiaogu.WeaponSkills.api.WeaponSkillAPI;

/**
 * 简单效果的工厂，自动注册
 * factory class of a simple effect with auto register
 * @author khjxiaogu
 * @time 2020年2月18日
 * file:SimpleEffectFactory.java
 */
public class SimpleEffectFactory implements EffectFactory {
	private SimpleEffect eff;
	public SimpleEffectFactory(SimpleEffect e) {
		// TODO Auto-generated constructor stub
		eff=e;
		WeaponSkillAPI.getAPI().registerEffectFactory(this);
	}

	@Override
	public EffectInstance createEffectInstance(Player p, int time, int level) {
		// TODO Auto-generated method stub
		return new SimpleEffectInstance(p,eff,time,level);
	}

	@Override
	public String getEffectIdentifier() {
		// TODO Auto-generated method stub
		return eff.getEffectIdentifier();
	}

}
