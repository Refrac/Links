package me.refrac.linkscore;

import me.refrac.linkscore.events.JoinQuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.refrac.linkscore.commands.*;
import me.refrac.linkscore.utils.*;

import java.io.File;
import java.io.IOException;

public class Links extends JavaPlugin {

    public static Links plugin;
    private File cfile;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        long startTiming = System.currentTimeMillis();
        Logger.INFO.out("Enabling LinksCore");

        Logger.INFO.out("Loading config files");
        createConfig();
        Logger.SUCCESS.out("Successfully loaded the config files");

        Logger.INFO.out("Loading commands");
        getCommand("links").setExecutor(new CMDLinks());
        Logger.SUCCESS.out("Successfully loaded the commands");

        Logger.INFO.out("Loading events");
        Bukkit.getServer().getPluginManager().registerEvents(new JoinQuitEvent(), this);
        Logger.SUCCESS.out("Successfully loaded the events");

        Logger.SUCCESS.out("LinksCore successfully enabled. (" + (System.currentTimeMillis() - startTiming) + "ms)");
        Logger.INFO.out("Report any issues or errors directly to the developers @ " + Utils.SUPPORT_URL);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        plugin = null;
        Logger.INFO.out("Shutting down LinksCore");
        Logger.SUCCESS.out("LinksCore successfully disabled.");
        Logger.INFO.out("Report any issues or errors directly to the developers @ " + Utils.SUPPORT_URL);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    // Create Config File
    private void createConfig() {
        cfile = new File (getDataFolder(), "config.yml");
        if (!cfile.exists()) {
            cfile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(cfile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    // Reload Config File
    public void reloadConfig() {
        cfile = new File(getDataFolder(), "config.yml");
        try {
            config = YamlConfiguration.loadConfiguration(cfile);
        } catch(Exception ex) {
            Logger.ERROR.out("Failed to reload the config file! Report this to the developer @ " + Utils.SUPPORT_URL);
        }
    }
}
