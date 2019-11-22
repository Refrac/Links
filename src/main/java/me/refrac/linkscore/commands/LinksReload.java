package me.refrac.linkscore.commands;

import me.refrac.linkscore.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinksReload implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player))
        {
            Bukkit.getServer().getLogger().warning("You can only use this command as a player!");
            return true;
        }
        final Player player = (Player) sender;
        if (!cmd.getName().equalsIgnoreCase("linksreload")) {
            return true;
        }
        if (!sender.hasPermission("links.reload")) {
            sender.sendMessage( ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.noPerm")));
            return true;
        }
        Core.plugin.reloadConfig();
        player.sendMessage( ChatColor.translateAlternateColorCodes ( '&', Core.plugin.getConfig().getString("Messages.reload" )));
        return true;
    }
}
