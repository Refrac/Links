package me.refrac.linkscore;

import java.io.File;

import me.refrac.linkscore.events.Chat;
import me.refrac.linkscore.events.JoinEvent;
import me.refrac.linkscore.utils.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.refrac.linkscore.commands.DiscordCommand;
import me.refrac.linkscore.commands.LinksCommand;
import me.refrac.linkscore.commands.StaffChatCommand;
import me.refrac.linkscore.commands.StoreCommand;
import me.refrac.linkscore.commands.WebsiteCommand;

public class LinksCore extends JavaPlugin
{
	public static LinksCore plugin;

	@Override
	public void onEnable()
	{
		UpdateChecker checker;

		Logger.log(Logger.LogLevel.OUTLINE,  "********************");
		Logger.log(Logger.LogLevel.INFO, "Initializing LinksCore Version: " + Settings.VERSION);
		Logger.log(Logger.LogLevel.INFO, "Created by: " + Settings.DEVELOPER_NAME);
		Logger.log(Logger.LogLevel.OUTLINE,  "********************");
		plugin = this;

		Logger.log(Logger.LogLevel.INFO, "Plugin Loading...");
		Logger.log(Logger.LogLevel.INFO, "Registering Managers...");
		registerEvents();
		Logger.log(Logger.LogLevel.INFO, "Managers Registered!");

		Logger.log(Logger.LogLevel.INFO, "Registering Commands...");
		registerCommands();
		Logger.log(Logger.LogLevel.INFO, "Commands Registered!");

		Logger.log(Logger.LogLevel.INFO, "Loading Config's...");
		this.createConfig();
		Logger.log(Logger.LogLevel.INFO, "Config's Registered!");

		Logger.log(Logger.LogLevel.SUCCESS, "LinksCore Version: " + Settings.VERSION + " Loaded.");
		Logger.log(Logger.LogLevel.OUTLINE,  "********************");

		Logger.log(Logger.LogLevel.INFO, "LinksCore Version: " + Settings.VERSION + " checking for updates...");
		checker = new UpdateChecker(LinksCore.plugin);
		if (checker.isConnected())
		{
             if (checker.hasUpdate())
             {
         		Logger.log(Logger.LogLevel.OUTLINE, "********************");
         		Logger.log(Logger.LogLevel.INFO, "LinksCore is outdated!");
         		Logger.log(Logger.LogLevel.INFO, "Newest version: " + checker.getLatestVersion());
         		Logger.log(Logger.LogLevel.INFO, "Your version: " + Settings.VERSION);
         		Logger.log(Logger.LogLevel.INFO, "Download: https://www.spigotmc.org/resources/70888/");
         		Logger.log(Logger.LogLevel.OUTLINE, "********************");
             }
         }
	}

	public void onDisable()
	{
		plugin = null;
	}
	
	private void registerCommands()
	{
		this.getCommand("discord").setExecutor(new DiscordCommand());
		this.getCommand("website").setExecutor(new WebsiteCommand());
		this.getCommand("store").setExecutor(new StoreCommand());
		this.getCommand("links").setExecutor(new LinksCommand());
		this.getCommand("staffchat").setExecutor(new StaffChatCommand());
	}

	private void registerEvents()
	{
		final PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new Chat(), this);
	}
	
	private void createConfig()
	{
	    try
		{
	        if(!getDataFolder().exists())
	        {
	        	getDataFolder().mkdirs();
			}

	        File file = new File(getDataFolder(), "config.yml");
	        if(!file.exists())
	        {
	            saveDefaultConfig();
	        }

	    }
	    catch(Exception exception)
		{
	        getLogger().info(exception.getMessage());
	    }
	}

}