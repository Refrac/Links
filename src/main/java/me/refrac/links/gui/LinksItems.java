package me.refrac.links.gui;

import me.refrac.links.Links;
import me.refrac.links.utils.ItemBuilder;
import me.refrac.links.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LinksItems {

    public static ItemStack websiteItem() {
        List<String> lore = new ArrayList<String>();
        for (String s : Links.getLinksConfig().getStringList("WebsiteItem.LORE")) {
            lore.add(Utils.color(s));
        }
        return new ItemBuilder(Material.valueOf(Links.getLinksConfig().getString("WebsiteItem.MATERIAL")))
                .setName(Utils.color(Links.getLinksConfig().getString("WebsiteItem.NAME")))
                .setLore(lore)
                .toItemStack();
    }

    public static ItemStack discordItem() {
        List<String> lore = new ArrayList<String>();
        for (String s : Links.getLinksConfig().getStringList("DiscordItem.LORE")) {
            lore.add(Utils.color(s));
        }
        return new ItemBuilder(Material.valueOf(Links.getLinksConfig().getString("DiscordItem.MATERIAL")))
                .setName(Utils.color(Links.getLinksConfig().getString("DiscordItem.NAME")))
                .setLore(lore)
                .toItemStack();
    }

    public static ItemStack teamspeakItem() {
        List<String> lore = new ArrayList<String>();
        for (String s : Links.getLinksConfig().getStringList("TeamspeakItem.LORE")) {
            lore.add(Utils.color(s));
        }
        return new ItemBuilder(Material.valueOf(Links.getLinksConfig().getString("TeamspeakItem.MATERIAL")))
                .setName(Utils.color(Links.getLinksConfig().getString("TeamspeakItem.NAME")))
                .setLore(lore)
                .toItemStack();
    }

    public static ItemStack storeItem() {
        List<String> lore = new ArrayList<String>();
        for (String s : Links.getLinksConfig().getStringList("StoreItem.LORE")) {
            lore.add(Utils.color(s));
        }
        return new ItemBuilder(Material.valueOf(Links.getLinksConfig().getString("StoreItem.MATERIAL")))
                .setName(Utils.color(Links.getLinksConfig().getString("StoreItem.NAME")))
                .setLore(lore)
                .toItemStack();
    }

    public static ItemStack twitterItem() {
        List<String> lore = new ArrayList<String>();
        for (String s : Links.getLinksConfig().getStringList("TwitterItem.LORE")) {
            lore.add(Utils.color(s));
        }
        return new ItemBuilder(Material.valueOf(Links.getLinksConfig().getString("TwitterItem.MATERIAL")))
                .setName(Utils.color(Links.getLinksConfig().getString("TwitterItem.NAME")))
                .setLore(lore)
                .toItemStack();
    }

    public static ItemStack allLinksItem() {
        List<String> lore = new ArrayList<String>();
        for (String s : Links.getLinksConfig().getStringList("AllLinksItem.LORE")) {
            lore.add(Utils.color(s));
        }
        return new ItemBuilder(Material.valueOf(Links.getLinksConfig().getString("AllLinksItem.MATERIAL")))
                .setName(Utils.color(Links.getLinksConfig().getString("AllLinksItem.NAME")))
                .setLore(lore)
                .toItemStack();
    }
}