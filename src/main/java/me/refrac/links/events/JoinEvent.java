/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.links.events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.links.Links;
import me.refrac.links.utils.PlayerJoinUtils;
import me.refrac.links.utils.UpdateChecker;
import me.refrac.links.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Zachary Baldwin / Refrac
 */
public class JoinEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent chatEvent) {
        Player player = chatEvent.getPlayer();
        if (Links.getLinksConfig().getBoolean("Update.Enabled")) {
            if (chatEvent.getPlayer().hasPermission("links.update")) {
                new UpdateChecker(Links.plugin, 90283).getLatestVersion( version -> {
                    if (!Links.plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                        player.sendMessage(Utils.color("&7&m-----------------------------------------"));
                        player.sendMessage(Utils.color("&bA new version of Links&7(Links " + version + ") &bhas been released!"));
                        player.sendMessage(Utils.color("&bPlease update here: &e" + Utils.getPluginURL));
                        player.sendMessage(Utils.color("&7&m-----------------------------------------"));
                }});
            }
        }
        if (Links.getLinksConfig().getBoolean("Messages.enabled")) {
            chatEvent.setJoinMessage(null);

            for (String m : Links.getLinksConfig().getStringList("Messages.join_motd")) {
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    player.sendMessage(PlaceholderAPI.setPlaceholders(player, Utils.color(m.replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName()))));
                } else {
                    player.sendMessage(Utils.color(m.replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
                }
            }

            if (!player.hasPlayedBefore()) {
                PlayerJoinUtils.instance.setJoinNumber(1);
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, Utils.color(Links.getLinksConfig().getString("Messages.first_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName()).replace("{joinnumber}", String.valueOf(PlayerJoinUtils.instance.getJoinNumber())))));
                } else {
                    Bukkit.broadcastMessage(Utils.color(Links.getLinksConfig().getString("Messages.first_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName()).replace("{joinnumber}", String.valueOf(PlayerJoinUtils.instance.getJoinNumber()))));
                }
            } else {
                if (player.hasPermission("links.silent.join")) {
                    player.sendMessage(Utils.color(Links.getLinksConfig().getString("Messages.silent_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
                } else {
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, Utils.color(Links.getLinksConfig().getString("Messages.join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName()))));
                    } else {
                        Bukkit.broadcastMessage(Utils.color(Links.getLinksConfig().getString("Messages.join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
                    }
                }
            }
        }
        if (player.getName().equals("Refrac")) {
            player.sendMessage(ChatColor.RED + "Debug Message");
            player.sendMessage(" ");
            player.sendMessage(ChatColor.GREEN + "This server is using " + Utils.getName + " version " + Utils.getVersion);
            player.sendMessage(" ");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent chatEvent) {
        if (Links.getLinksConfig().getBoolean("Messages.enabled")) {
            Player player = chatEvent.getPlayer();

            chatEvent.setQuitMessage(null);

            if (player.hasPermission("links.silent.quit")) {
                player.sendMessage(Utils.color(Links.getLinksConfig().getString("Messages.silent_quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
            } else {
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, Utils.color(Links.getLinksConfig().getString("Messages.quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName()))));
                } else {
                    Bukkit.broadcastMessage(Utils.color(Links.getLinksConfig().getString("Messages.quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
                }
            }
        }
    }
}