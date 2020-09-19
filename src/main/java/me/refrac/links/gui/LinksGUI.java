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
        return Utils.color(Links.getLinksConfig().getString("GUI.Title"));
    }

    private int getSize() {
        return 9*Links.getLinksConfig().getInt("GUI.Rows");
    }

    public Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, getSize(), getTitle());

        inv.setItem(Links.getLinksConfig().getInt("WebsiteItem.SLOT"), LinksItems.websiteItem());
        inv.setItem(Links.getLinksConfig().getInt("DiscordItem.SLOT"), LinksItems.discordItem());
        inv.setItem(Links.getLinksConfig().getInt("TeamspeakItem.SLOT"), LinksItems.teamspeakItem());
        inv.setItem(Links.getLinksConfig().getInt("StoreItem.SLOT"), LinksItems.storeItem());
        inv.setItem(Links.getLinksConfig().getInt("TwitterItem.SLOT"), LinksItems.twitterItem());
        if (getSize() >= 36) {
            inv.setItem(Links.getLinksConfig().getInt("AllLinksItem.SLOT"), LinksItems.allLinksItem());
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
        // CLICK EVENTS
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