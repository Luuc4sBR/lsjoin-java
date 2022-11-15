package me.luuc4s.br;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main main;
	
	@Override
	public void onEnable() {
		main = this;
		Bukkit.getConsoleSender().sendMessage("§aPlugin habilitado!");
		Bukkit.getPluginManager().registerEvents(new JoinQuit(), this);
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§cPlugin desabilitado!");
	}
	
}
