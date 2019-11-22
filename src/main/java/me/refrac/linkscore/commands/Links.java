package me.refrac.linkscore.commands;

import me.refrac.linkscore.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import me.refrac.linkscore.utils.*;

public class Links implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender , Command cmd , String label , String[] args) {
        if (!(sender instanceof Player))
        {
            Bukkit.getServer().getLogger().warning("You can only use this command as a player!");
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission ( "links.help" )) {
            if (args.length == 0) {
                player.sendMessage ( ChatColor.YELLOW + "********************" );
                player.sendMessage ( "" );
                player.sendMessage ( ChatColor.RED + "Commands:" );
                player.sendMessage ( ChatColor.GOLD + "/links (this help page)" );
                player.sendMessage ( ChatColor.GOLD + "/links update (Checks for a update)" );
                player.sendMessage ( ChatColor.GOLD + "/links about (Shows plugin info)" );
                player.sendMessage ( ChatColor.GOLD + "/links perms (Shows all perms)" );
                player.sendMessage ( ChatColor.GOLD + "/linksreload (Reloads the config)" );
                player.sendMessage ( ChatColor.GOLD + "/discord (Discord Message)" );
                player.sendMessage ( ChatColor.GOLD + "/store (Store Message)" );
                player.sendMessage ( ChatColor.GOLD + "/website (Website Message)" );
                player.sendMessage ( ChatColor.GOLD + "/teamspeak (TeamSpeak Message)" );
                player.sendMessage ( ChatColor.GOLD + "/staffchat (StaffChat)" );
                player.sendMessage ( "" );
                player.sendMessage ( ChatColor.YELLOW + "********************" );
            } else {
                switch (args[0]) {
                    case "update":
                        if (player.hasPermission ( "links.update" )) {
                            player.sendMessage ( ChatColor.translateAlternateColorCodes ( '&', Core.plugin.getConfig().getString ("Messages.noPerm" ) ) );
                        }
                        sender.sendMessage ( ChatColor.RED + "Checking for updates..." );
                        UpdateChecker checker = new UpdateChecker ( Core.plugin );
                        if (checker.isConnected ()) {
                            if (checker.hasUpdate ()) {
                                player.sendMessage ( ChatColor.GRAY + "********************" );
                                player.sendMessage ( "" );
                                player.sendMessage ( ChatColor.RED + "LinksCore is outdated!" );
                                player.sendMessage ( ChatColor.GREEN + "Newest version: " + checker.getLatestVersion () );
                                player.sendMessage ( ChatColor.RED + "Your version: " + Settings.VERSION );
                                player.sendMessage ( ChatColor.GOLD + "Download: " + Settings.PLUGIN_URL );
                                player.sendMessage ( "" );
                                player.sendMessage ( ChatColor.GRAY + "********************" );
                            } else {
                                player.sendMessage ( ChatColor.GREEN + "LinksCore is up to date!" );
                            }
                        }
                        return true;

                    case "about":
                        if (player.hasPermission ( "links.about" )) {
                            player.sendMessage ( ChatColor.translateAlternateColorCodes ( '&' , Core.plugin.getConfig().getString ( "Messages.noPerm" ) ) );
                        }
                            player.sendMessage ( ChatColor.YELLOW + "********************" );
                            player.sendMessage ( "" );
                            player.sendMessage ( ChatColor.RED + "Plugin Info:" );
                            player.sendMessage ( ChatColor.GOLD + "Plugin made by " + ChatColor.GREEN + Settings.DEVELOPER_NAME );
                            player.sendMessage ( ChatColor.RED + "Plugin Contributors:" );
                            player.sendMessage ( ChatColor.GOLD + "http://bit.ly/2OJO5HP" );
                            player.sendMessage ( ChatColor.GOLD + "Current plugin version " + ChatColor.GREEN + Settings.VERSION );
                            player.sendMessage ( ChatColor.RED + "Support:" );
                            player.sendMessage ( ChatColor.GOLD + "https://discord.io/RefracDev" );
                            player.sendMessage ( ChatColor.RED + "Get All Commands: " );
                            player.sendMessage ( ChatColor.GOLD + "/links" );
                            player.sendMessage ( "" );
                            player.sendMessage ( ChatColor.YELLOW + "********************" );
                            return true;
                    case "perms":
                        if (player.hasPermission ( "links.perms" )) {
                            player.sendMessage ( ChatColor.translateAlternateColorCodes ( '&' , Core.plugin.getConfig().getString ( "Messages.noPerm" ) ) );
                        }
                        player.sendMessage ( "" );
                        player.sendMessage ( ChatColor.RED + "Permssions:" );
                        player.sendMessage ( ChatColor.GOLD + "links.update (Update message on-join)" );
                        player.sendMessage ( ChatColor.GOLD + "(If enabled in config.yml)" );
                        player.sendMessage ( ChatColor.GOLD + "links.help (The help page)" );
                        player.sendMessage ( ChatColor.GOLD + "links.about (The about page)" );
                        player.sendMessage ( ChatColor.GOLD + "links.reload (Reloads the config)" );
                        player.sendMessage ( ChatColor.GOLD + "links.perms (Permissions page)" );
                        player.sendMessage ( ChatColor.GOLD + "staffchat.use (Access to /staffchat(/sc, /sctoggle))" );
                        player.sendMessage ( ChatColor.GOLD + "staffchat.see (Access to see staffchat)" );
                        player.sendMessage ( ChatColor.GOLD + "vanish.use (Access to /vanish(/v)" );
                        player.sendMessage ( "" );
                        return true;
                        }
                }
                return false;
            }
            return false;
        }
    }
