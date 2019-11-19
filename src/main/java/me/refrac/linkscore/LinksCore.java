package me.refrac.linkscore;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.refrac.linkscore.commands.*;
import me.refrac.linkscore.events.*;
import me.refrac.linkscore.utils.*;

public class LinksCore extends JavaPlugin
{
    public static LinksCore plugin;

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        UpdateChecker checker;

        Logger.log(Logger.LogLevel.OUTLINE,  "********************");
        Logger.log(Logger.LogLevel.INFO, "Initializing LinksCore Version: " + Settings.VERSION);
        Logger.log(Logger.LogLevel.INFO, "Created by: " + Settings.DEVELOPER_NAME);
        Logger.log(Logger.LogLevel.INFO, "Plugin URL: " + Settings.PLUGIN_URL);
        Logger.log(Logger.LogLevel.INFO, "Support URL: " + Settings.SUPPORT_URL);
        Logger.log(Logger.LogLevel.OUTLINE,  "********************");
        plugin = this;

        Logger.log(Logger.LogLevel.INFO, "Plugin Loading...");
        Logger.log(Logger.LogLevel.INFO, "Registering Managers...");
        regEvents();
        Logger.log(Logger.LogLevel.INFO, "Managers Registered!");

        Logger.log(Logger.LogLevel.INFO, "Registering Commands...");
        regCmds();
        Logger.log(Logger.LogLevel.INFO, "Commands Registered!");

        Logger.log(Logger.LogLevel.INFO, "Loading Config's...");
        saveDefaultConfig();
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
                Logger.log(Logger.LogLevel.INFO, "Download: " + Settings.PLUGIN_URL);
                Logger.log(Logger.LogLevel.OUTLINE, "********************");
            }
        }
    }
    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        plugin = null;
    }

    private void regCmds() {
        getCommand("links").setExecutor(new LinksCommand());
        getCommand("linksreload").setExecutor (new LinksReloadCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("website").setExecutor(new WebsiteCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("teamspeak").setExecutor(new TSCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
    }

    private void regEvents() {
        final PluginManager pm = getServer ().getPluginManager ();
        pm.registerEvents ( new JoinEvent () , this );
        pm.registerEvents ( new Chat () , this );
    }
    }
