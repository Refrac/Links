package me.refrac.linkscore.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.refrac.linkscore.Main;

public class JoinMessage implements Listener {

	private Main plugin;
	
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        
        List<String> messages =  Main.plugin.getConfig().getStringList("message");
        
        for (String message : messages) {
        	p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
    }
}
