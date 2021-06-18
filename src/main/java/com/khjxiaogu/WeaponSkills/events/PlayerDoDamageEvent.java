package com.khjxiaogu.WeaponSkills.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class PlayerDoDamageEvent implements Cancellable {
	EntityDamageByEntityEvent oev;

	public PlayerDoDamageEvent(EntityDamageByEntityEvent ev) {
		oev = ev;
	}

	public double getDamage() {
		return oev.getDamage();
	}

	public void setDamage(double damage) {
		oev.setDamage(damage);
	}

	public Player getSource() {
		return (Player) oev.getDamager();
	}

	public LivingEntity getTarget() {
		return (LivingEntity) oev.getEntity();
	}

	public DamageCause getCause() {
		return oev.getCause();
	}

	@Override
	public boolean isCancelled() {
		return oev.isCancelled();
	}

	@Override
	public void setCancelled(boolean cancel) {
		oev.setCancelled(cancel);
	}
}
