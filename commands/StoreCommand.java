package me.refrac.linkscore.commands;

import me.refrac.linkscore.LinksCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StoreCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
			Bukkit.getServer().getLogger().warning(LinksCore.plugin.getConfig().getString("NoConsole"));
			return true;
		}

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("StoreMessage")));
        return true;
    }
}