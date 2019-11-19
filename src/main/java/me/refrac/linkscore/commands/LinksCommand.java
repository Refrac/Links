package me.refrac.linkscore.commands;

import me.refrac.linkscore.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import me.refrac.linkscore.utils.*;

public class LinksCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender , Command cmd , String label , String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getServer ().getLogger ().warning ( "You can only use this command as a player!" );
            return true;
        }

        if (sender.hasPermission ( "links.help" )) {
            if (args.length == 0) {
                sender.sendMessage ( ChatColor.YELLOW + "********************" );
                sender.sendMessage ( "" );
                sender.sendMessage ( ChatColor.GRAY + "" + ChatColor.BOLD + "LinksCore 1.6 Help" );
                sender.sendMessage ( "" );
                sender.sendMessage ( ChatColor.RED + "Commands:" );
                sender.sendMessage ( ChatColor.GOLD + "/links (this help page)" );
                sender.sendMessage ( ChatColor.GOLD + "/links update (Checks for a update)" );
                sender.sendMessage ( ChatColor.GOLD + "/links about (Shows plugin info)" );
                sender.sendMessage ( ChatColor.GOLD + "/linksreload (Reloads the config)" );
                sender.sendMessage ( ChatColor.GOLD + "/discord (Discord Message)" );
                sender.sendMessage ( ChatColor.GOLD + "/store (Store Message)" );
                sender.sendMessage ( ChatColor.GOLD + "/website (Website Message)" );
                sender.sendMessage ( ChatColor.GOLD + "/teamspeak (TeamSpeak Message)" );
                sender.sendMessage ( ChatColor.GOLD + "/staffchat (StaffChat)" );
                sender.sendMessage ( "" );
                sender.sendMessage ( ChatColor.RED + "Permssions:" );
                sender.sendMessage ( ChatColor.GOLD + "links.update (Update message on-join)" );
                sender.sendMessage ( ChatColor.GOLD + "(If enabled in config.yml)" );
                sender.sendMessage ( ChatColor.GOLD + "links.help (This help page)" );
                sender.sendMessage ( ChatColor.GOLD + "links.about (The about page)" );
                sender.sendMessage ( ChatColor.GOLD + "links.reload (Reloads the config)" );
                sender.sendMessage ( ChatColor.GOLD + "staffchat.use (Access to /staffchat(/sc, /sctoggle))" );
                sender.sendMessage ( ChatColor.GOLD + "staffchat.see (Access to see staffchat)" );
                sender.sendMessage ( "" );
                sender.sendMessage ( ChatColor.YELLOW + "********************" );
            } else {
                switch (args[0]) {
                    case "update":
                        if (sender.hasPermission ( "links.update" )) {
                            sender.sendMessage ( ChatColor.translateAlternateColorCodes ( '&', "NoPerm" ));
                        }
                        sender.sendMessage ( ChatColor.RED + "Checking for updates..." );
                        UpdateChecker checker = new UpdateChecker ( LinksCore.plugin );
                        if (checker.isConnected ()) {
                            if (checker.hasUpdate ()) {
                                sender.sendMessage ( ChatColor.GRAY + "********************" );
                                sender.sendMessage ( "" );
                                sender.sendMessage ( ChatColor.RED + "LinksCore is outdated!" );
                                sender.sendMessage ( ChatColor.GREEN + "Newest version: " + checker.getLatestVersion () );
                                sender.sendMessage ( ChatColor.RED + "Your version: " + Settings.VERSION );
                                sender.sendMessage ( ChatColor.GOLD + "Download: " + Settings.PLUGIN_URL );
                                sender.sendMessage ( "" );
                                sender.sendMessage ( ChatColor.GRAY + "********************" );
                            } else {
                                sender.sendMessage ( ChatColor.GREEN + "LinksCore is up to date!" );
                            }
                        }
                        return true;

                    case "about":
                        if (sender.hasPermission ( "links.about" )) {
                            sender.sendMessage ( ChatColor.translateAlternateColorCodes ( '&' , "NoPerm" ) );
                        }
                            sender.sendMessage ( ChatColor.YELLOW + "********************" );
                            sender.sendMessage ( "" );
                            sender.sendMessage ( ChatColor.RED + "Plugin Info:" );
                            sender.sendMessage ( ChatColor.GOLD + "Plugin made by " + ChatColor.GREEN + Settings.DEVELOPER_NAME );
                            sender.sendMessage ( ChatColor.RED + "Plugin Contributors:" );
                            sender.sendMessage ( ChatColor.GOLD + "http://bit.ly/2OJO5HP" );
                            sender.sendMessage ( ChatColor.GOLD + "Current plugin version " + ChatColor.GREEN + Settings.VERSION );
                            sender.sendMessage ( ChatColor.RED + "Support:" );
                            sender.sendMessage ( ChatColor.GOLD + "https://discord.io/RefracDev" );
                            sender.sendMessage ( ChatColor.RED + "Get All Commands: " );
                            sender.sendMessage ( ChatColor.GOLD + "/links" );
                            sender.sendMessage ( "" );
                            sender.sendMessage ( ChatColor.YELLOW + "********************" );
                            return true;
                        }
                }
                return false;
            }
            return false;
        }
    }
