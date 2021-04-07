/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.links.utils;

import org.bukkit.ChatColor;

/**
 * @author Zachary Baldwin / Refrac
 */
public class Utils {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getName = "Links";
    public static String getAuthor = "Refrac";
    public static String getSupport = "https://discord.com/invite/mUKjWZByzQ";
    public static String getVersion = "2.4";
    public static String getPluginURL = "https://www.spigotmc.org/resources/90283/";
    public static String getDevMessage = ChatColor.RED + "This command can only be used by Refrac for support reasons.";
    public static String getDevMessage2 = ChatColor.GRAY + "If you are worried about what this command does check it out on GitHub here:";
    public static String getDevMessage3 = ChatColor.GRAY + "https://bit.ly/3fF74At";

}