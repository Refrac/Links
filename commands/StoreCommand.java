package me.refrac.linkscore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.refrac.linkscore.Main;

public class StoreCommand implements org.bukkit.command.CommandExecutor {
	
  public StoreCommand() {}
  
  @SuppressWarnings("unused")
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
		if (!(sender instanceof Player)) {
			Bukkit.getServer().getLogger().warning(Main.plugin.getConfig().getString("NoConsole"));
			return true;
		}
	  Player p = (Player) sender;
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("StoreMessage")));
      return true;
		}
  {
  }
}