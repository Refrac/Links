package me.refrac.linkscore.commands;

import me.refrac.linkscore.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import me.refrac.linkscore.utils.*;

import java.util.List;

public class CMDLinks implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender , Command cmd , String label , String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("You can only use this command as a player!");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            for (String m : Links.plugin.getConfig().getStringList("Links")) {
                player.sendMessage(Utils.color(m.replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName()).replace("{arrowright}", "\u00BB").replace("{circle}", "\u2B24")));
            }
        } else if (args.length == 1) {
            if (!player.hasPermission ( "links.help" )) {
                player.sendMessage(Utils.color(Links.plugin.getConfig().getString("Messages.no_permission")));
                return false;
            }
            this.sendHelpMessage(player);
            return true;
        } else if (args[0].equalsIgnoreCase("about")) {
            if (!player.hasPermission("links.about")) {
                player.sendMessage(Utils.color(Links.plugin.getConfig().getString("Messages.no_permission")));
                return false;
            }
            player.sendMessage(Utils.color("&7&l&m-------------------------------------------"));
            player.sendMessage("");
            player.sendMessage(ChatColor.YELLOW + "Created by: " + ChatColor.AQUA + Utils.getAuthor);
            player.sendMessage(ChatColor.YELLOW + "Version: " + ChatColor.AQUA + Utils.getVersion);
            player.sendMessage(ChatColor.YELLOW + "Support: " + ChatColor.AQUA + Utils.getSupport);
            player.sendMessage("");
            player.sendMessage(Utils.color("&7&l&m-------------------------------------------"));
            return true;
        } else if (args[0].equalsIgnoreCase("reload")) {
            if (!player.hasPermission("links.reload")) {
                player.sendMessage(Utils.color(Links.plugin.getConfig().getString("Messages.no_permission")));
                return false;
            }
            Links.plugin.reloadConfig();
            player.sendMessage(Utils.color(Utils.getPrefix + "&eConfig files successfully reloaded."));
            return true;
        }
        return false;
    }

    public void sendHelpMessage(Player player) {
        player.sendMessage(Utils.color("&7&m-------------------------------------------"));
        player.sendMessage("");
        player.sendMessage(Utils.color("&b&lLinksCore &bv" + Utils.getVersion));
        player.sendMessage(Utils.color("&eby &b" + Utils.getAuthor));
        player.sendMessage("");
        player.sendMessage(Utils.color("&b/links &7| &eShows all of the server links"));
        player.sendMessage(Utils.color("&b/links help &7| &eThis help page"));
        player.sendMessage(Utils.color("&b/links about &7| &eShows plugin info"));
        player.sendMessage(Utils.color("&b/links reload &7| &eReloads the config files"));
        player.sendMessage("");
        player.sendMessage(Utils.color("&7&m-------------------------------------------"));
    }
}