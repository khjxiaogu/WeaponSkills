package com.khjxiaogu.WeaponSkills.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class PlayerDamagedEvent implements Cancellable {
	EntityDamageEvent oev;

	public PlayerDamagedEvent(EntityDamageEvent ev) {
		oev = ev;
	}

	public double getDamage() {
		return oev.getDamage();
	}

	public void setDamage(double damage) {
		oev.setDamage(damage);
	}

	public Player getTarget() {
		return (Player) oev.getEntity();
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
