package com.github.kaitoyuuki.MagicArrows;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class PlayerShootBowEvent extends EntityShootBowEvent {

	
	public PlayerShootBowEvent(EntityShootBowEvent event) {
		super(event.getEntity(), event.getBow(), (Projectile) event.getProjectile(), event.getForce());
	}

}


