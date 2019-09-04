package me.refrac.linkscore;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.refrac.linkscore.commands.DiscordCommand;
import me.refrac.linkscore.commands.LinksCommand;
import me.refrac.linkscore.commands.StaffChatCommand;
import me.refrac.linkscore.commands.StoreCommand;
import me.refrac.linkscore.commands.WebsiteCommand;
import me.refrac.linkscore.utils.Chat;
import me.refrac.linkscore.utils.JoinMessage;
import me.refrac.linkscore.utils.Logger;
import me.refrac.linkscore.utils.Settings;
import me.refrac.linkscore.utils.UpdateChecker;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

	public static Main plugin;
	private static Main instance;
	private UpdateChecker checker;
	
	@SuppressWarnings("static-access")
	public void onEnable() {
		 Main.plugin = this;
		 PluginDescriptionFile VarUtilType = this.getDescription();
		 Logger.log(Logger.LogLevel.OUTLINE,  "********************");
	     Logger.log(Logger.LogLevel.INFO, "Initializing LinksCore Version: " + Settings.VERSION);   
	     Logger.log(Logger.LogLevel.INFO, "Created by: " + Settings.DEVELOPER_NAME);
	     Logger.log(Logger.LogLevel.OUTLINE,  "********************");
	     Logger.log(Logger.LogLevel.INFO, "Plugin Loading...");
	     Logger.log(Logger.LogLevel.INFO, "Registering Managers...");
	     this.plugin = this;
	     this.instance = this;
	     registerEvents();
	     Logger.log(Logger.LogLevel.INFO, "Managers Registered!");
	     Logger.log(Logger.LogLevel.INFO, "Registering Commands...");	     
	     registerCommands();     
	     Logger.log(Logger.LogLevel.INFO, "Commands Registered!");
	     Logger.log(Logger.LogLevel.INFO, "Loading Config's...");
	     this.createConfig();
	     Logger.log(Logger.LogLevel.INFO, "Config's Registered!");
	     Logger.log(Logger.LogLevel.SUCCESS, "LinksCore Version: " + Settings.VERSION + " Loaded.");
	     this.setEnabled(true);
	     Logger.log(Logger.LogLevel.OUTLINE,  "********************");
	     Logger.log(Logger.LogLevel.INFO, "LinksCore Version: " + Settings.VERSION + " checking for updates...");
         this.checker = new UpdateChecker(Main.plugin);
         if (this.checker.isConnected()) {
             if (this.checker.hasUpdate()) { 
         Logger.log(Logger.LogLevel.OUTLINE, "********************");
         Logger.log(Logger.LogLevel.INFO, "LinksCore is outdated!");
         Logger.log(Logger.LogLevel.INFO, "Newest version: " + this.checker.getLatestVersion());
         Logger.log(Logger.LogLevel.INFO, "Your version: " + Settings.VERSION);
         Logger.log(Logger.LogLevel.INFO, "Download: https://www.spigotmc.org/resources/70888/");
         Logger.log(Logger.LogLevel.OUTLINE, "********************");
		{
			
		}
             }
         }
}
	
	public void onDisable() {
		// Plugin shutdown logic
	}
	
	public void registerCommands() {
		this.getCommand("discord").setExecutor(new DiscordCommand());
		this.getCommand("website").setExecutor(new WebsiteCommand());
		this.getCommand("store").setExecutor(new StoreCommand());
		this.getCommand("links").setExecutor(new LinksCommand());
		this.getCommand("staffchat").setExecutor(new StaffChatCommand());
	}

	public void registerEvents() {
		final PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinMessage(), this);
        pm.registerEvents(new Chat(), this);
	}
	
	private File configf;
    private FileConfiguration config;
	
	private void createConfig(){
	    try{
	        if(!getDataFolder().exists()) getDataFolder().mkdirs();

	        File file = new File(getDataFolder(), "config.yml");
	        if(!file.exists()){
	            saveDefaultConfig();
	        }

	    }catch(Exception exception){
	        getLogger().info(exception.getMessage());
	    }
	}

	public BukkitTask runTaskAsynchronously(final Runnable run) {
        return this.getScheduler().runTaskAsynchronously(this, run);
    }
	}