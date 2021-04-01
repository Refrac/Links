/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.links.utils;

/**
 * @author Zachary Baldwin / Refrac
 */
public class PlayerJoinUtils {

    public static PlayerJoinUtils instance;
    int joinNumber;

    public PlayerJoinUtils() {
        this.joinNumber = 0;
    }

    public Integer getJoinNumber() {
        return this.joinNumber;
    }

    public void setJoinNumber(int amt) {
        setJoinNumber(getJoinNumber() + amt);
    }
}
