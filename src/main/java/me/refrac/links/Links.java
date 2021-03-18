package me.refrac.links;

import me.refrac.links.events.JoinQuitEvent;
import me.refrac.links.gui.LinksGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.refrac.links.commands.*;
import me.refrac.links.utils.*;

import java.io.File;
import java.io.IOException;

public class Links extends JavaPlugin {

    public static Links plugin;
    private File cfile;
    private static FileConfiguration config;

    private LinksGUI linksGUI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        long startTiming = System.currentTimeMillis();
        Logger.INFO.out("Enabling Links");

        Logger.NONE.out("");
        Logger.NONE.out(ChatColor.LIGHT_PURPLE + " _       ___   _    _   _  __   ____   ");
        Logger.NONE.out(ChatColor.LIGHT_PURPLE + "| |     |_ _| | |  | | | |/ /  / ___|  ");
        Logger.NONE.out(ChatColor.LIGHT_PURPLE + "| |      | |  |      | | ' /    ___    ");
        Logger.NONE.out(ChatColor.LIGHT_PURPLE + "| |___   | |  | |  | | | .      ___) | " + ChatColor.YELLOW + "By " + Utils.getAuthor);
        Logger.NONE.out(ChatColor.LIGHT_PURPLE + "|_____| |___| |_|  |_| |_| |_| |____/  " + ChatColor.YELLOW + "v" + Utils.getVersion);
        Logger.NONE.out("");

        Logger.INFO.out("Loading config files");
        this.createConfig();
        Logger.SUCCESS.out("Successfully loaded the config files");

        Logger.INFO.out("Loading commands");
        this.getCommand("links").setExecutor(new CMDLinks());
        Logger.SUCCESS.out("Successfully loaded the commands");

        Logger.INFO.out("Loading events");
        Bukkit.getServer().getPluginManager().registerEvents(new JoinQuitEvent(), this);
        this.linksGUI = new LinksGUI(this);
        Logger.SUCCESS.out("Successfully loaded the events");

        Logger.SUCCESS.out("Links successfully enabled. (" + (System.currentTimeMillis() - startTiming) + "ms)");
        Logger.INFO.out("Report any issues or errors directly to the developers @ " + Utils.getSupport);

        new UpdateChecker(Links.plugin, 90283).getLatestVersion(version -> {
            if (!Links.plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                Logger.NONE.out(Utils.color("&7&m-----------------------------------------"));
                Logger.NONE.out(Utils.color("&bA new version of Links&7(Links " + version + ") &bhas been released!"));
                Logger.NONE.out(Utils.color("&bPlease update here: " + Utils.getPluginURL));
                Logger.NONE.out(Utils.color("&7&m-----------------------------------------"));
            } else
                Logger.NONE.out(ChatColor.GREEN + "Links is up to date!");
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        plugin = null;
        Logger.INFO.out("Shutting down Links");
        Logger.SUCCESS.out("Links successfully disabled.");
        Logger.INFO.out("Report any issues or errors directly to the developers @ " + Utils.getSupport);
    }

    public static FileConfiguration getLinksConfig() {
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
            Logger.ERROR.out("Failed to reload the config file! Report this to the developer @ " + Utils.getSupport);
        }
    }

    public LinksGUI getLinksGUI() {
        return linksGUI;
    }
}