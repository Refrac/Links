package me.refrac.linkscore.commands;

import me.refrac.linkscore.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Vanish implements CommandExecutor {

    private String enabled = ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Vanish.enabled").replace ( "{prefix}", ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.prefix" ))));
    private String disabled = ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Vanish.disabled").replace ( "{prefix}", ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.prefix" ))));
    private String noPerm = ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.noPerm"));

    @Override
    public boolean onCommand(CommandSender sender , Command command , String label , String[] args) {
        if (command.getName().equalsIgnoreCase("vanish")) {
            return true;
        }
        if (!(sender instanceof Player))
        {
            Bukkit.getServer().getLogger().warning("You can only use this command as a player!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {

            if (!player.hasPermission("vanish.use" )) {
                player.sendMessage (noPerm);
                return true;
            }
                player.showPlayer ( player );
                player.setAllowFlight ( false );
                player.setFlying ( false );
                player.sendMessage(disabled);
                return true;
            } else {
                player.hidePlayer ( player );
                player.setAllowFlight ( true );
                player.setFlying ( true );
                player.sendMessage(enabled);
                return true;
            }
        }
    }
