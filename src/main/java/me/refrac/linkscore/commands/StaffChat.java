package me.refrac.linkscore.commands;

import java.util.ArrayList;

import me.refrac.linkscore.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

    public static ArrayList<Player> Insc = new ArrayList<Player> ();

    private String enabled = ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("StaffChat.enabled").replace("{prefixsc}", ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("StaffChat.prefixSC"))));
    private String disabled = ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("StaffChat.disabled").replace("{prefixsc}", ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("StaffChat.prefixSC"))));
    private String noPerm = ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("Messages.noPerm"));

    @Override
    public boolean onCommand(CommandSender sender , Command command , String label , String[] args) {
        if (command.getName().equalsIgnoreCase("staffchat")) {
            return true;
        }
            if (!(sender instanceof Player))
            {
                Bukkit.getServer().getLogger().warning("You can only use this command as a player!");
                return true;
            }

            Player player = (Player) sender;

            if (args.length == 0) {

                if (!player.hasPermission("staffchat.use" )) {
                    player.sendMessage (noPerm);
                    return true;
                }

                if (Insc.contains(player)) {
                    Insc.remove(player);
                    player.sendMessage(disabled);
                    return true;
                } else {
                    Insc.add(player);
                    player.sendMessage(enabled);
                    return true;
                }
            }
        return false;
    }
    }