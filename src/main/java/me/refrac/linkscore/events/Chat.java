package me.refrac.linkscore.events;

import me.refrac.linkscore.LinksCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.linkscore.commands.StaffChatCommand;

public class Chat implements Listener
{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();

        String prefix = ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("PrefixSC"));
        String suffix = ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("Suffix"));
        String msg = e.getMessage();

        if(StaffChatCommand.Insc.contains(p))
        {
            e.setCancelled(true);

            for(Player staff : Bukkit.getServer().getOnlinePlayers())
            {
                if(staff.hasPermission("staffchat.see"))
                {
                    staff.sendMessage(prefix + " " + p.getName() + suffix + ChatColor.translateAlternateColorCodes ('&', msg));
                }
            }
        }
    }
}