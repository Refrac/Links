package me.refrac.links.commands;

import me.refrac.links.Links;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import me.refrac.links.utils.*;

public class CMDLinks implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.WARNING.out("You can only use this command as a player!");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            for (String m : Links.getLinksConfig().getStringList("Links")) {
                player.sendMessage(Utils.color(m));
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("gui")) {
                if (Links.getLinksConfig().getBoolean("GUI.Enabled")) {
                    player.openInventory(Links.plugin.getLinksGUI().getInventory());
                    return true;
                } else {
                    player.sendMessage(Utils.color(Links.getLinksConfig().getString("GUI.DisabledMessage")));
                }
            } else if (args[0].equalsIgnoreCase("help")) {
                if (!player.hasPermission("links.help")) {
                    player.sendMessage(Utils.color(Links.getLinksConfig().getString("Messages.no_permission")));
                    return false;
                }
                this.sendHelpMessage(player);
                return true;
            } else if (args[0].equalsIgnoreCase("about")) {
                if (!player.hasPermission("links.about")) {
                    player.sendMessage(Utils.color(Links.getLinksConfig().getString("Messages.no_permission")));
                    return false;
                }
                player.sendMessage(Utils.color("&7&m---------------&7[ &d&lLINKS ABOUT &7]&7&m---------------"));
                player.sendMessage("");
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Created by: " + ChatColor.AQUA + Utils.getAuthor);
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Version: " + ChatColor.AQUA + Utils.getVersion);
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Support: " + ChatColor.AQUA + Utils.getSupport);
                player.sendMessage("");
                player.sendMessage(Utils.color("&7&m---------------&7[ &d&lLINKS ABOUT &7]&7&m---------------"));
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (!player.hasPermission("links.reload")) {
                    player.sendMessage(Utils.color(Links.getLinksConfig().getString("Messages.no_permission")));
                    return false;
                }
                Links.plugin.reloadConfig();
                player.sendMessage(Utils.color(Utils.getPrefix + "&eConfig files successfully reloaded."));
                return true;
            }
            return false;
        }
        return false;
    }

    public void sendHelpMessage(Player player) {
        player.sendMessage(Utils.color("&7&m---------------&7[ &d&lLINKS HELP &7]&7&m---------------"));
        player.sendMessage("");
        player.sendMessage(Utils.color("&d/links &7| &eShows all of the server links"));
        player.sendMessage(Utils.color("&d/links gui &7| &eOpens the Links GUI"));
        player.sendMessage(Utils.color("&d/links help &7| &eThis help page"));
        player.sendMessage(Utils.color("&d/links about &7| &eShows plugin info"));
        player.sendMessage(Utils.color("&d/links reload &7| &eReloads the config files"));
        player.sendMessage("");
        player.sendMessage(Utils.color("&7&m---------------&7[ &d&lLINKS HELP &7]&7&m---------------"));
    }
}