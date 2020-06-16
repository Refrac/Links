package me.refrac.linkscore.commands;

import me.refrac.linkscore.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import me.refrac.linkscore.utils.*;

public class CMDLinks implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender , Command cmd , String label , String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("You can only use this command as a player!");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            for (String links : Links.plugin.getConfig().getStringList("Links")) {
                links = Utils.setupPlaceholderAPI(player, links);
                player.sendMessage(Utils.color(links));
            }
        } else if (args.length == 1) {
            if (!player.hasPermission ( "links.help" )) {
                player.sendMessage(Utils.color( Links.plugin.getConfig().getString("Messages.noPermission")));
                return false;
            }
            player.sendMessage(Utils.color("&7&m-------------------------------------------"));
            player.sendMessage("");
            player.sendMessage(ChatColor.AQUA + "/links " + ChatColor.WHITE + "Shows all of the server links");
            player.sendMessage(ChatColor.AQUA + "/links help " + ChatColor.WHITE + "this help page");
            player.sendMessage(ChatColor.AQUA + "/links about " + ChatColor.WHITE + "Shows plugin info");
            player.sendMessage(ChatColor.AQUA + "/links reload " + ChatColor.WHITE + "Reloads the config");
            player.sendMessage("");
            player.sendMessage(Utils.color("&7&m-------------------------------------------"));
            return true;
        } else if (args[0].equalsIgnoreCase("about")) {
            if (!player.hasPermission("links.about")) {
                player.sendMessage(Utils.color( Links.plugin.getConfig().getString("Messages.noPermission")));
                return false;
            }
            player.sendMessage(Utils.color("&7&l&m-------------------------------------------"));
            player.sendMessage("");
            player.sendMessage(ChatColor.YELLOW + "Created by: " + ChatColor.AQUA + Utils.DEVELOPER_NAME);
            player.sendMessage(ChatColor.YELLOW + "Support: " + ChatColor.AQUA + Utils.SUPPORT_URL);
            player.sendMessage(ChatColor.YELLOW + "Version: " + ChatColor.AQUA + Utils.VERSION);
            player.sendMessage("");
            player.sendMessage(Utils.color("&7&l&m-------------------------------------------"));
            return true;
        } else if (args[0].equalsIgnoreCase("reload")) {
            if (!player.hasPermission("links.reload")) {
                player.sendMessage(Utils.color( Links.plugin.getConfig().getString("Messages.noPermission")));
                return false;
            }
            Links.plugin.reloadConfig();
            player.sendMessage(Utils.color("&7Config files successfully reloaded."));
            return true;
        }
        return false;
    }
}