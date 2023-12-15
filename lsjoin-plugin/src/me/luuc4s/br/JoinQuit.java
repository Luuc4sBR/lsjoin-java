package me.luuc4s.br;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuit implements Listener {

	public static boolean ativarjoinac;
	public static boolean ativarjoinch;

	static {
		ativarjoinac = Main.main.getConfig().getBoolean("ActionBar.Ativar");
		ativarjoinch = Main.main.getConfig().getBoolean("Chat.Ativar");
	}

	@EventHandler
	public static void Entrar(PlayerJoinEvent j) {

		for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			Player p = j.getPlayer();
			if (ativarjoinac == true) {
				ActionBar.sendActionBarMessage(onlinePlayers, (Main.main.getConfig().getString("ActionBar.Mensagem-join")
						.replace("&", "§").replace("%player%", p.getName())));
			}
			if (ativarjoinch == true) {
				j.setJoinMessage(Main.main.getConfig().getString("Chat.Mensagem-join").replace("&", "§")
						.replace("%player%", p.getName()));
			} else {
				j.setJoinMessage(null);
				return;
			}}}

	@EventHandler
	public static void Sair(PlayerQuitEvent q) {

		for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			Player p = q.getPlayer();
			if (ativarjoinac == true) {
				ActionBar.sendActionBarMessage(onlinePlayers, (Main.main.getConfig().getString("ActionBar.Mensagem-quit")
						.replace("&", "§").replace("%player%", p.getName())));
			}
			if (ativarjoinch == true) {
				q.setQuitMessage(Main.main.getConfig().getString("Chat.Mensagem-quit").replace("&", "§")
						.replace("%player%", p.getName()));
			} else {
				q.setQuitMessage(null);
				return;
			}}}}
