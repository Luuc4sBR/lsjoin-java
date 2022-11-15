package me.luuc4s.br;

import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class JoinQuit implements Listener {
	
	public static boolean ativarjoinac;
	public static boolean ativarjoinch;

	@EventHandler
	public static void Entrar(PlayerJoinEvent j) {		
				
		Player p = j.getPlayer();
			
		ativarjoinac = Main.main.getConfig().getBoolean("ActionBar.Ativar");
		ativarjoinch = Main.main.getConfig().getBoolean("Chat.Ativar");
		
		if (ativarjoinac == true) {
			ActionBar.sendActionBarMessage(p, (Main.main.getConfig().getString("ActionBar.Mensagem-join").replace("&", "§").replace("%player%", p.getName())));
		}
		if (ativarjoinch == true) {
			j.setJoinMessage(Main.main.getConfig().getString("Chat.Mensagem-join").replace("&", "§").replace("%player%", p.getName()));
		} else {
			j.setJoinMessage(null);
			return;
	}
}
	
	@EventHandler
	public static void Sair(PlayerQuitEvent q) {
		
		Player p = q.getPlayer();
		
		ativarjoinac = Main.main.getConfig().getBoolean("ActionBar.Ativar");
		ativarjoinch = Main.main.getConfig().getBoolean("Chat.Ativar");
				
		if (ativarjoinac == true) {
			ActionBar.sendActionBarMessage(p, (Main.main.getConfig().getString("ActionBar.Mensagem-quit").replace("&", "§").replace("%player%", p.getName())));
		}
		if (ativarjoinch == true) {
			q.setQuitMessage(Main.main.getConfig().getString("Chat.Mensagem-quit").replace("&", "§").replace("%player%", p.getName()));
		} else {
			q.setQuitMessage(null);		
			return;
		}
	}
}	
