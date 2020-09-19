/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.links.utils;

import org.bukkit.Bukkit;

// Created by Refrac/Zachstyles 5/10/20
public enum Logger {

    NONE('r'), SUCCESS('a'), ERROR('c'), WARNING('e'), INFO('b');

    char color;

    Logger(char color) { this.color = color; }

    public void out(String message) {
        message = Utils.color(String.format("%s > &%c%s", "&dLinks", this.color, message));
        Bukkit.getConsoleSender().sendMessage(message);
    }
}