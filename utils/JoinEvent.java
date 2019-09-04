package me.refrac.linkscore.utils;

import org.bukkit.event.player.*;

import me.refrac.linkscore.Main;
import me.refrac.linkscore.utils.UpdateChecker;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class JoinEvent implements Listener
{
	public UpdateChecker checker;
	
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
    	Player p = e.getPlayer();
    	if (p.hasPermission("links.update")) {
    		if (Main.getPlugin(null).getConfig().getBoolean("Update.Enabled") == true){
    		this.checker = new UpdateChecker(Main.plugin);
                        if (this.checker.isConnected()) {
                            if (this.checker.hasUpdate()) {
                            	p.sendMessage(ChatColor.GRAY + "=========================");
                                p.sendMessage(ChatColor.RED + "Links is outdated!");
                                p.sendMessage(ChatColor.GREEN + "Newest version: " + this.checker.getLatestVersion());
                                p.sendMessage(ChatColor.RED + "Your version: " + Settings.VERSION);
                                p.sendMessage(ChatColor.GOLD + "Download: https://www.spigotmc.org/resources/70888/");
                                p.sendMessage(ChatColor.GRAY + "=========================");
                            }
                        }               
       }
    }
}
}