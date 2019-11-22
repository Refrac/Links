package me.refrac.linkscore.commands;

import me.refrac.linkscore.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Discord implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            Bukkit.getServer().getLogger().warning("You can only use this command as a player!");
            return true;
        }

        Player player = (Player) sender;

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.discord").replace ( "{prefix}", ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.prefix" )))));
        return true;
    }
}