package me.refrac.links.events;

import me.refrac.links.Links;
import me.refrac.links.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent chatEvent) {
        if (Links.getLinksConfig().getBoolean("Messages.enabled")) {
            Player player = chatEvent.getPlayer();

            chatEvent.setJoinMessage(null);

            for (String m : Links.getLinksConfig().getStringList("Messages.join_motd")) {
                player.sendMessage(Utils.color(m.replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
            }

            if (!player.hasPlayedBefore()) {
                Bukkit.broadcastMessage(Utils.color(Links.getLinksConfig().getString("Messages.first_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
            } else {
                if (player.hasPermission("links.silent.join")) {
                    player.sendMessage(Utils.color(Links.getLinksConfig().getString("Messages.silent_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
                } else {
                    Bukkit.broadcastMessage(Utils.color(Links.getLinksConfig().getString("Messages.join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent chatEvent) {
        if (Links.getLinksConfig().getBoolean("Messages.enabled")) {
            Player player = chatEvent.getPlayer();

            chatEvent.setQuitMessage(null);

            if (player.hasPermission("links.silent.quit")) {
                player.sendMessage(Utils.color(Links.getLinksConfig().getString("Messages.silent_quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
            } else {
                Bukkit.broadcastMessage(Utils.color(Links.getLinksConfig().getString("Messages.quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
            }
        }
    }
}