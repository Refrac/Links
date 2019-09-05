package me.refrac.linkscore.commands;

import me.refrac.linkscore.LinksCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.refrac.linkscore.utils.Settings;
import me.refrac.linkscore.utils.UpdateChecker;

public class LinksCommand implements CommandExecutor
{
	private UpdateChecker checker;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			Bukkit.getServer().getLogger().warning(LinksCore.plugin.getConfig().getString("NoConsole"));
			return true;
		}

		if(sender.hasPermission("links.help"))
		{
			if (args.length == 0)
			{
				sender.sendMessage(ChatColor.YELLOW + "********************");
				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.GRAY + " " + ChatColor.BOLD + "LinksCore 1.2 Help");
				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.RED + "Commands:");
				sender.sendMessage(ChatColor.GOLD + "/links (this help page)");
				sender.sendMessage(ChatColor.GOLD + "/discord (Discord Message)");
				sender.sendMessage(ChatColor.GOLD + "/store (Store Message)");
				sender.sendMessage(ChatColor.GOLD + "/website (Website Message)");
				sender.sendMessage(ChatColor.GOLD + "/staffchat (StaffChat)");
				sender.sendMessage(ChatColor.GOLD + "/links update (Checks for a update)");
				sender.sendMessage(ChatColor.GOLD + "/links about (Shows plugin info)");
				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.RED + "Permssions:");
				sender.sendMessage(ChatColor.GOLD + "links.update (Update message on-join)");
				sender.sendMessage(ChatColor.GOLD + "(If enabled in config.yml)");
				sender.sendMessage(ChatColor.GOLD + "links.help (this help page)");
				sender.sendMessage(ChatColor.GOLD + "staffchat.use (Access to /staffchat(/sc, /sctoggle))");
				sender.sendMessage(ChatColor.GOLD + "staffchat.see (Access to see staffchat)");
				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.YELLOW + "********************");
			}
			else
			{
				switch(args[0])
				{
					case "update":
						sender.sendMessage(ChatColor.RED + "Checking for updates...");
						this.checker = new UpdateChecker(LinksCore.plugin);
						if (this.checker.isConnected())
						{
							if (this.checker.hasUpdate())
							{
								sender.sendMessage(ChatColor.GRAY + "********************");
								sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Links is outdated!");
								sender.sendMessage(ChatColor.GREEN + "Newest version: " + this.checker.getLatestVersion());
								sender.sendMessage(ChatColor.RED + "Your version: " + Settings.VERSION);
								sender.sendMessage(ChatColor.GOLD + "Download: https://www.spigotmc.org/resources/70888/");
								sender.sendMessage(ChatColor.GRAY + "********************");
							}
							else
							{
								sender.sendMessage(ChatColor.GREEN + "Links is up to date!");
							}
						}
						return true;

					case "about":
						sender.sendMessage(ChatColor.YELLOW + "********************");
						sender.sendMessage(" ");
						sender.sendMessage(ChatColor.RED + "Plugin Info:");
						sender.sendMessage(ChatColor.GOLD + "Plugin made by Refrac");
						sender.sendMessage(ChatColor.GOLD + "Current plugin version " + ChatColor.GREEN + Settings.VERSION);
						sender.sendMessage(ChatColor.RED + "Support:");
						sender.sendMessage(ChatColor.GOLD + "https://discord.gg/acpxjpF");
						sender.sendMessage("Get All Commands: ");
						sender.sendMessage("/links");
						sender.sendMessage(" ");
						sender.sendMessage(ChatColor.YELLOW + "********************");
						return true;
				}
			}
	  	}

		return false;
	}
}