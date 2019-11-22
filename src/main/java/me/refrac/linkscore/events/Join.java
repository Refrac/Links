package me.refrac.linkscore.events;

import me.refrac.linkscore.*;
import me.refrac.linkscore.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.event.player.*;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.*;

import java.util.List;

public class Join implements Listener
{

    @EventHandler
    public void onJoin(final PlayerJoinEvent event)
    {
        // Check if plugin is outdated
        Player player = event.getPlayer();
        if(player.hasPermission("links.update"))
        {
            if (Core.plugin.getConfig().getBoolean("Update.enabled"))
            {
                UpdateChecker checker = new UpdateChecker( Core.plugin);
                if (checker.isConnected())
                {
                    if(checker.hasUpdate())
                    {
                        player.sendMessage(ChatColor.GRAY + "*************************");
                        player.sendMessage(ChatColor.RED + "LinksCore is outdated!");
                        player.sendMessage(ChatColor.GREEN + "Newest version: " + checker.getLatestVersion());
                        player.sendMessage(ChatColor.RED + "Your version: " + Settings.VERSION);
                        player.sendMessage(ChatColor.GOLD + "Download: " + Settings.PLUGIN_URL);
                        player.sendMessage(ChatColor.GRAY + "*************************");
                    }
                }
            }
        }

        // Broadcasted Join Message Ex. {player} joined back
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.joinMessage").replace ( "{player}", player.getName())));
        // If new player
        if (!player.hasPlayedBefore()) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.newJoinMessage").replace("{player}", player.getName())));
        }

        // Player Join Message(Only sends to player not broadcasted)
        List<String> messages = Core.plugin.getConfig().getStringList("message");

        for (String message : messages)
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    @EventHandler
    public void onQuit (PlayerQuitEvent event) {
        Player player = event.getPlayer();
        // Broadcasted Quit Message Ex. {player} left
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.quitMessage").replace("{player}", player.getName())));
    }
}