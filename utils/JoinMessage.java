package me.refrac.linkscore.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.refrac.linkscore.Main;

public class JoinMessage implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final String joinMessage = event.getJoinMessage();
        Main.runTaskAsynchronously(new Runnable() {
            @Override
            public void run() {
                delayedJoin(event.getPlayer(), joinMessage);
            }
        });
    }
        public void delayedJoin(final Player player, final String message) {
            if (!player.isOnline()) {
                return;
            }
    }
}