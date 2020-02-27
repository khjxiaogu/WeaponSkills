package com.khjxiaogu.WeaponSkills.effect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.khjxiaogu.WeaponSkills.WeaponSkills;
import com.khjxiaogu.khjxiaogu.TimeUtil;

public class PlayerEffects {
	Player owner;
	Map<EffectFactory,EffectInstance> effects=new ConcurrentHashMap<>();
	public PlayerEffects(Player owner) {
		this.owner=owner;
		// TODO Auto-generated constructor stub
	}
	public void giveEffect(EffectFactory effect,int time,int level) {
		effects.put(effect,effect.createEffectInstance(owner, time, level));
	}
	public boolean hasEffect(EffectFactory effect) {
		return effects.containsKey(effect);
	}
	public EffectInstance getEffect(EffectFactory effect) {
		return effects.get(effect);
	}
	public void tickEffects() {
		if(effects.isEmpty())return;
		long time=TimeUtil.getTime();
		for(Map.Entry<EffectFactory, EffectInstance> efftime:effects.entrySet()) {
			if(efftime.getValue().getExpireTime()<time) {
				efftime.getValue().onRemove();
				effects.remove(efftime.getKey());
			}else
				new BukkitRunnable() {
					EffectInstance efftime;
					public BukkitRunnable setEff(EffectInstance efftime2) {
						this.efftime=efftime2;
						return this;
					}
					@Override
					public void run() {
						efftime.onTick();
					}
				}.setEff(efftime.getValue()).runTask(WeaponSkills.plugin);
			}

	}
	public void clear() {
		effects.clear();
	}
	public void onDoDamage(EntityDamageByEntityEvent ev) {
		// TODO Auto-generated method stub
		for(EffectInstance efftime:effects.values()) {
			efftime.onDoDamage(ev);
		}
	}
	public void onBeDamaged(EntityDamageEvent ev) {
		// TODO Auto-generated method stub
		for(EffectInstance efftime:effects.values()) {
			efftime.onBeDamaged(ev);
		}
	}
	public void onTick() {
		// TODO Auto-generated method stub
		/*for(EffectInstance efftime:effects.values()) {
			efftime.onTick();
		}*/
		tickEffects();
	}
}
