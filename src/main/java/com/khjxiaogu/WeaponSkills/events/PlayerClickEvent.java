package com.khjxiaogu.WeaponSkills.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerClickEvent implements Cancellable {
	Entity ClickedEntity;
	Block ClickedBlock;
	PlayerEvent oev;

	public Entity getClickedEntity() {
		return ClickedEntity;
	}

	public Block getClickedBlock() {
		return ClickedBlock;
	}

	public Player getSource() {
		return oev.getPlayer();
	}

	public boolean isEntity() {
		return ClickedEntity != null;
	}

	public boolean isBlock() {
		return ClickedEntity == null;
	}

	public PlayerClickEvent(PlayerInteractEntityEvent ev) {
		oev = ev;
		ClickedEntity = ev.getRightClicked();
	}

	public PlayerClickEvent(PlayerInteractEvent ev) {
		oev = ev;
		if (ev.getMaterial() != Material.AIR) {
			ClickedBlock = ev.getClickedBlock();
		}
	}

	@Override
	public boolean isCancelled() {
		return ((Cancellable) oev).isCancelled();
	}

	@Override
	public void setCancelled(boolean cancel) {
		((Cancellable) oev).setCancelled(cancel);
	}
}
