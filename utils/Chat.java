package me.refrac.linkscore.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.linkscore.Main;
import me.refrac.linkscore.commands.StaffChatCommand;

public class Chat implements Listener {
	
	public Main plugin;
	
	public Chat() {
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		
		Player p = e.getPlayer();
		
		String Prefix = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Prefix"));
		String msg = e.getMessage();
		
		if(StaffChatCommand.Insc.contains(p)) {
		
			e.setCancelled(true);
			
			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {	
				
				if(staff.hasPermission("staffchat.see")) {
					
					staff.sendMessage(Prefix + ChatColor.GREEN + p.getDisplayName() + ChatColor.DARK_GRAY + ": " + ChatColor.BOLD + msg);
				
			}	
	}
}
}
}