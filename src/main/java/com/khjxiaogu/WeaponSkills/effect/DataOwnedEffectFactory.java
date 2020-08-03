package com.khjxiaogu.WeaponSkills.effect;

import org.bukkit.entity.Player;

import com.khjxiaogu.WeaponSkills.api.WeaponSkillAPI;

public class DataOwnedEffectFactory implements EffectFactory {
	private Class<DataOwnedEffect> eff;
	private String id;
	public DataOwnedEffectFactory(Class<DataOwnedEffect> e)throws Exception {
		// TODO Auto-generated constructor stub
		id=e.getConstructor().newInstance().getEffectIdentifier();
		eff=e;
		WeaponSkillAPI.getAPI().registerEffectFactory(this);
	}

	@Override
	public EffectInstance createEffectInstance(Player p, int time, int level) throws RuntimeException {
		// TODO Auto-generated method stub
		try {
			return new DataOwnedEffectInstance(p,eff,time,level);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getEffectIdentifier() {
		// TODO Auto-generated method stub
		return id;
	}

}
