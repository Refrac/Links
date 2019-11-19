package me.refrac.linkscore.commands;

import java.util.ArrayList;
import java.util.Objects;

import me.refrac.linkscore.LinksCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {

    public static ArrayList<Player> Insc = new ArrayList<Player> ();

    String enabled = ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("Enabled").replace("{prefixsc}", ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("PrefixSC"))));
    String disabled = ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("Disabled").replace("{prefixsc}", ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("PrefixSC"))));
    String noPerm = ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("NoPerm"));

    @Override
    public boolean onCommand(CommandSender sender , Command cmd , String label , String[] args) {
        if (cmd.getName().equalsIgnoreCase("staffchat")) {
            if (!(sender instanceof Player)) {
                Bukkit.getServer().getLogger().warning("You can only use this command as a player!");
                return true;
            }

            Player p = (Player) sender;

            if (args.length == 0) {

                if (!p.hasPermission("staffchat.use" )) {
                    p.sendMessage (noPerm);
                    return true;
                }

                if (Insc.contains(p)) {
                    Insc.remove(p);
                    p.sendMessage(disabled);
                    return true;
                } else {
                    Insc.add(p);
                    p.sendMessage(enabled);
                    return true;
                }
            }
        }
        return false;
    }
}