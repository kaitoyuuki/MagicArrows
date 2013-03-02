package com.github.kaitoyuuki.MagicArrows;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class MagicArrows extends JavaPlugin {
	
	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new BowListener(this), this);
	}
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
}
