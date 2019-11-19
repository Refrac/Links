package me.refrac.linkscore.commands;

import me.refrac.linkscore.LinksCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinksReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getServer().getLogger().warning("You can only use this command as a player!");
            return true;
        }
        final Player p = (Player) sender;
        if (!cmd.getName().equalsIgnoreCase("linksreload")) {
            return true;
        }
        if (!sender.hasPermission("links.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("NoPerm")));
            return true;
        }
        LinksCore.plugin.reloadConfig();
        p.sendMessage( ChatColor.translateAlternateColorCodes ( '&', LinksCore.plugin.getConfig().getString("Reload" )));
        return true;
    }




}
