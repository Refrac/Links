package me.refrac.linkscore.events;

import me.refrac.linkscore.Links;
import me.refrac.linkscore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent chatEvent) {
        if (Links.plugin.getConfig().getBoolean("Messages.enabled") == true) {
            Player player = chatEvent.getPlayer();

            chatEvent.setJoinMessage(null);

            for (String m : Links.plugin.getConfig().getStringList("Messages.join_motd")) {
                player.sendMessage(Utils.colorFormat(player, m.replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName()).replace("{arrowright}", "\u00BB").replace("{circle}", "\u2B24")));
            }

            if (!player.hasPlayedBefore()) {
                Bukkit.broadcastMessage(Utils.colorFormat(player, Links.plugin.getConfig().getString("Messages.first_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
            } else {
                if (player.hasPermission("links.silent.join")) {
                    player.sendMessage(Utils.colorFormat(player, Links.plugin.getConfig().getString("Messages.silent_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
                    return;
                } else {
                    Bukkit.broadcastMessage(Utils.colorFormat(player, Links.plugin.getConfig().getString("Messages.join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
                }
            }
        }
        if (Links.plugin.getConfig().getBoolean("Debug.enabled") == true) {
            if (chatEvent.getPlayer().getName().equals("Refrac")) {
                chatEvent.getPlayer().sendMessage("");
                chatEvent.getPlayer().sendMessage(ChatColor.GREEN + "LinksCore Information");
                chatEvent.getPlayer().sendMessage("");
                chatEvent.getPlayer().sendMessage(ChatColor.WHITE + "Plugin Name: " + ChatColor.GREEN + Links.plugin.getDescription().getName());
                chatEvent.getPlayer().sendMessage(ChatColor.WHITE + "Plugin Version: " + ChatColor.GREEN + Utils.getVersion);
                chatEvent.getPlayer().sendMessage(ChatColor.WHITE + "Plugin Author: " + ChatColor.GREEN + Links.plugin.getDescription().getAuthors());
                chatEvent.getPlayer().sendMessage("");
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent chatEvent) {
        if (Links.plugin.getConfig().getBoolean("Messages.enabled") == true) {
            Player player = chatEvent.getPlayer();

            chatEvent.setQuitMessage(null);

            if (player.hasPermission("links.silent.quit")) {
                player.sendMessage(Utils.colorFormat(player, Links.plugin.getConfig().getString("Messages.silent_quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
                return;
            } else {
                Bukkit.broadcastMessage(Utils.colorFormat(player, Links.plugin.getConfig().getString("Messages.quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
            }
        }
    }
}
