package me.refrac.links.gui;

import me.refrac.links.Links;
import me.refrac.links.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LinksGUI implements Listener {

    private Links main;

    public LinksGUI(Links main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents((Listener)this, main);
    }

    private String getTitle() {
        if (Links.getLinksConfig().getString("GUI.Title").length() <= 32) {
            return Utils.color(Links.getLinksConfig().getString("GUI.Title"));
        } else return Utils.color("&d&lLinks");
    }

    private int getSize() {
        if (Links.getLinksConfig().getInt("GUI.Rows") <= 6 && !(Links.getLinksConfig().getInt("GUI.Rows") <= 0)) {
            return 9*Links.getLinksConfig().getInt("GUI.Rows");
        } else return 54;
    }

    public Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, getSize(), getTitle());
        // WebsiteItem
        if (Links.getLinksConfig().getInt("WebsiteItem.SLOT") <= 53 && !(Links.getLinksConfig().getInt("WebsiteItem.SLOT") >= getSize())) {
            inv.setItem(Links.getLinksConfig().getInt("WebsiteItem.SLOT"), LinksItems.websiteItem());
        } else if (getSize() >= 9) {
            inv.setItem(2, LinksItems.websiteItem());
        } else if (getSize() >= 36) {
            inv.setItem(11, LinksItems.allLinksItem());
        }
        // DiscordItem
        if (Links.getLinksConfig().getInt("DiscordItem.SLOT") <= 53 && !(Links.getLinksConfig().getInt("DiscordItem.SLOT") >= getSize())) {
            inv.setItem(Links.getLinksConfig().getInt("DiscordItem.SLOT"), LinksItems.discordItem());
        } else if (getSize() >= 9) {
            inv.setItem(3, LinksItems.discordItem());
        } else if (getSize() >= 36) {
            inv.setItem(12, LinksItems.discordItem());
        }
        // TeamspeakItem
        if (Links.getLinksConfig().getInt("TeamspeakItem.SLOT") <= 53 && !(Links.getLinksConfig().getInt("TeamspeakItem.SLOT") >= getSize())) {
            inv.setItem(Links.getLinksConfig().getInt("TeamspeakItem.SLOT"), LinksItems.teamspeakItem());
        } else if (getSize() >= 9) {
            inv.setItem(4, LinksItems.teamspeakItem());
        } else if (getSize() >= 36) {
            inv.setItem(13, LinksItems.teamspeakItem());
        }
        // StoreItem
        if (Links.getLinksConfig().getInt("StoreItem.SLOT") <= 53 && !(Links.getLinksConfig().getInt("StoreItem.SLOT") >= getSize())) {
            inv.setItem(Links.getLinksConfig().getInt("StoreItem.SLOT"), LinksItems.storeItem());
        } else if (getSize() >= 9) {
            inv.setItem(5, LinksItems.storeItem());
        } else if (getSize() >= 36) {
            inv.setItem(14, LinksItems.storeItem());
        }
        if (Links.getLinksConfig().getInt("TwitterItem.SLOT") <= 53 && !(Links.getLinksConfig().getInt("TwitterItem.SLOT") >= getSize())) {
            inv.setItem(Links.getLinksConfig().getInt("TwitterItem.SLOT"), LinksItems.twitterItem());
        } else if (getSize() >= 9) {
            inv.setItem(6, LinksItems.twitterItem());
        } else if (getSize() >= 36) {
            inv.setItem(15, LinksItems.twitterItem());
        }
        // AllLinksItem
        if (Links.getLinksConfig().getInt("AllLinksItem.SLOT") <= 53 && !(Links.getLinksConfig().getInt("AllLinksItem.SLOT") >= getSize())) {
            inv.setItem(Links.getLinksConfig().getInt("AllLinksItem.SLOT"), LinksItems.allLinksItem());
        } else if (getSize() >= 9) {
            inv.setItem(8, LinksItems.allLinksItem());
        } else if (getSize() >= 36) {
            inv.setItem(31, LinksItems.allLinksItem());
        }
        for (int i = 0; i < getSize(); i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, Glass());
            }
        }
        return inv;
    }

    private ItemStack Glass() {
        ItemStack stone = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName("");
        stone.setItemMeta(stonem);
        return stone;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView() == null) return;
        if (!event.getView().getTitle().equals(getTitle())) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getType() == Material.AIR) return;
        event.setCancelled(true);
        /*CLICK EVENTS*/
        if (event.getCurrentItem().isSimilar(LinksItems.websiteItem())) {
            player.sendMessage(Links.getLinksConfig().getString("GUI.WebsiteLink").replace("&", "§"));
            player.getOpenInventory().close();
        } else if (event.getCurrentItem().isSimilar(LinksItems.discordItem())) {
            player.sendMessage(Links.getLinksConfig().getString("GUI.DiscordLink").replace("&", "§"));
            player.getOpenInventory().close();
        } else if (event.getCurrentItem().isSimilar(LinksItems.teamspeakItem())) {
            player.sendMessage(Links.getLinksConfig().getString("GUI.TeamspeakLink").replace("&", "§"));
            player.getOpenInventory().close();
        } else if (event.getCurrentItem().isSimilar(LinksItems.storeItem())) {
            player.sendMessage(Links.getLinksConfig().getString("GUI.StoreLink").replace("&", "§"));
            player.getOpenInventory().close();
        } else if (event.getCurrentItem().isSimilar(LinksItems.twitterItem())) {
            player.sendMessage(Links.getLinksConfig().getString("GUI.TwitterLink").replace("&", "§"));
            player.getOpenInventory().close();
        } else if (event.getCurrentItem().isSimilar(LinksItems.allLinksItem())) {
            for (String m : Links.getLinksConfig().getStringList("Links")) {
                player.sendMessage(Utils.color(m));
            }
            player.getOpenInventory().close();
        }
    }
}