package me.refrac.linkscore.commands;

import me.refrac.linkscore.LinksCore;
import me.refrac.linkscore.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StoreCommand implements CommandExecutor
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            Bukkit.getServer().getLogger().warning("You can only use this command as a player!");
            return true;
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("Store").replace ( "{prefix}", ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("Prefix" )))));
        return true;
    }
}