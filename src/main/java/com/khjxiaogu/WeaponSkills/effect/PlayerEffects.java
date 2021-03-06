package com.khjxiaogu.WeaponSkills.effect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.khjxiaogu.WeaponSkills.WeaponSkills;
import com.khjxiaogu.WeaponSkills.events.PlayerBeDamageEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDamagedEvent;
import com.khjxiaogu.WeaponSkills.events.PlayerDoDamageEvent;
import com.khjxiaogu.WeaponSkills.skill.SkillPriority;
import com.khjxiaogu.khjxiaogu.TimeUtil;

public class PlayerEffects {
	Player owner;
	Map<EffectFactory, EffectInstance> effects = new ConcurrentHashMap<>();

	public PlayerEffects(Player owner) {
		this.owner = owner;
		// TODO Auto-generated constructor stub
	}

	public void giveEffect(EffectFactory effect, int time, int level) {
		effects.put(effect, effect.createEffectInstance(owner, time, level));
	}

	public boolean hasEffect(EffectFactory effect) {
		return effects.containsKey(effect);
	}

	public EffectInstance getEffect(EffectFactory effect) {
		return effects.get(effect);
	}

	public void tickEffects() {
		if (effects.isEmpty())
			return;
		long time = TimeUtil.getTime();
		for (Map.Entry<EffectFactory, EffectInstance> efftime : effects.entrySet()) {
			if (efftime.getValue().getExpireTime() < time) {
				effects.remove(efftime.getKey());
				efftime.getValue().onRemove(true);
			} else {
				new BukkitRunnable() {
					EffectInstance efftime;

					public BukkitRunnable setEff(EffectInstance efftime2) {
						efftime = efftime2;
						return this;
					}

					@Override
					public void run() {
						efftime.onTick();
					}
				}.setEff(efftime.getValue()).runTask(WeaponSkills.plugin);
			}
		}

	}

	public void clear() {
		for (EffectInstance ei : effects.values()) {
			ei.onRemove(false);
		}
		effects.clear();
	}

	public void onDoDamage(PlayerDoDamageEvent ev, SkillPriority p) {
		// TODO Auto-generated method stub
		for (EffectInstance efftime : effects.values()) {
			if (efftime.getExecutionPriority() == p) {
				efftime.onDoDamage(ev);
				if (ev.isCancelled())
					return;
			}
		}
	}

	public void onBeDamaged(PlayerDamagedEvent ev, SkillPriority p) {
		// TODO Auto-generated method stub
		for (EffectInstance efftime : effects.values()) {
			if (efftime.getExecutionPriority() == p) {
				efftime.onBeDamaged(ev);
				if (ev.isCancelled())
					return;
			}
		}
	}

	public void onBeDamagedByEntity(PlayerBeDamageEvent ev, SkillPriority p) {
		// TODO Auto-generated method stub
		for (EffectInstance efftime : effects.values()) {
			if (efftime.getExecutionPriority() == p) {
				efftime.onBeDamagedByEntity(ev);
				if (ev.isCancelled())
					return;
			}
		}
	}

	public void onTick() {
		// TODO Auto-generated method stub
		/*
		 * for(EffectInstance efftime:effects.values()) {
		 * efftime.onTick();
		 * }
		 */
		tickEffects();
	}
}
