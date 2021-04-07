/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.links.commands;

import me.refrac.links.Links;
import me.refrac.links.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Zachary Baldwin / Refrac
 */
public class LinksReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("links.reload")) {
            sender.sendMessage(Utils.color(Links.getLinksConfig().getString("Messages.no_permission")));
            return false;
        }
        Links.plugin.reloadConfig();
        sender.sendMessage(Utils.color(Links.getLinksConfig().getString("Messages.reload_message")));
        return false;
    }
}