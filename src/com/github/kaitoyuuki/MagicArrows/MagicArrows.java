package com.github.kaitoyuuki.MagicArrows;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.palmergames.bukkit.towny.Towny;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import com.turt2live.metrics.MagicArrows.EMetrics;
import com.turt2live.metrics.MagicArrows.tracker.BasicTracker;



public class MagicArrows extends JavaPlugin {
	private Towny towny = null;
	private WorldGuardPlugin worldguard = null;
	
	public BasicTracker torch, fire, tnt, smite, fireball, ice;
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		getConfig();
		checkPlugins();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new BowListener(this), this);
		try {
			String graph = "Arrow Usage";
			EMetrics metrics = new EMetrics(this);
			
			torch = EMetrics.createBasicTracker(graph, "torch");
			fire = EMetrics.createBasicTracker(graph, "fire");
			tnt = EMetrics.createBasicTracker(graph, "TNT");
			smite = EMetrics.createBasicTracker(graph, "Lightning");
			fireball = EMetrics.createBasicTracker(graph, "Fireball");
			ice = EMetrics.createBasicTracker(graph, "Ice");
			
			metrics.addTracker(torch);
			metrics.addTracker(fire);
			metrics.addTracker(tnt);
			metrics.addTracker(smite);
			metrics.addTracker(fireball);
			metrics.addTracker(ice);
			
			metrics.startMetrics();
		} catch (IOException e) {
			
		}
	}
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
	public void incTorch() {
		torch.increment();
	}
	public void incFire() {
		fire.increment();
	}
	public void incTNT() {
		tnt.increment();
	}
	public void incSmite() {
		smite.increment();
	}
	public void incFireBall() {
		fireball.increment();
	}
	public void incIce() {
		ice.increment();
	}
	private void checkPlugins() {
		Plugin test;
		Logger MALogger = getLogger();
		test = getServer().getPluginManager().getPlugin("Towny");
		if (test == null) {
			// Towny's not running, don't check for town/plot permissions
			MALogger.log(Level.INFO, "Towny is not enabled. Skipping plot checks.");
			
		}
		else {
			// Towny is enabled, check for town/plot permissions
			MALogger.log(Level.INFO, "Towny is enabled! Arrows will now require plot permissions.");
		}
		test = getServer().getPluginManager().getPlugin("WorldGuard");
		if (test == null) {
			//Worldguard is not running, don't check for region permissions/flags
			MALogger.log(Level.INFO, "WorldGuard is not enabled. Skipping region checks.");
			
		}
		else {
			
			// Worldguard is running, check for region permissions/flags
			MALogger.log(Level.INFO, "WorldGuard is enabled! Arrows will now require region permissions.");
		}
	}
	public Towny getTowny() {
		
		return towny;
	}
	public WorldGuardPlugin getWG() {
		
		return worldguard;
	}
}
