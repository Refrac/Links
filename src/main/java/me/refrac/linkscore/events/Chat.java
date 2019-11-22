package me.refrac.linkscore.events;

import me.refrac.linkscore.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.linkscore.commands.StaffChat;

public class Chat implements Listener
{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();

        String prefix = ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("StaffChat.prefixSC"));
        String suffix = ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("StaffChat.suffix"));
        String msg = event.getMessage();

        if(StaffChat.Insc.contains(player))
        {
            event.setCancelled(true);

            for(Player staff : Bukkit.getServer().getOnlinePlayers())
            {
                if(staff.hasPermission("staffchat.see"))
                {
                    staff.sendMessage(prefix + " " + player.getName() + suffix + msg);
                }
            }
        }
    }
}