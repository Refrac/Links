package me.refrac.linkscore.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.refrac.linkscore.Main;

public class StaffChatCommand implements CommandExecutor {

	  public Main plugin;
	
	  public StaffChatCommand() {
	  }
	
	public static ArrayList<Player> Insc = new ArrayList<Player>();
	
	String Prefix = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Prefix"));
	String Enabled = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Enabled"));
	String Disabled = ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Disabled"));
	String NoPerm = ChatColor.translateAlternateColorCodes('&', Prefix + Main.plugin.getConfig().getString("NoPerm"));
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("staffchat")) {
		
			if (!(sender instanceof Player)) {
				
				Bukkit.getServer().getLogger().warning(Main.plugin.getConfig().getString("NoConsole"));
				
				return true;
				
			}
		  Player p = (Player) sender;
		  
		if(args.length == 0) {
			
			if(!p.hasPermission("staffchat.use")) {
				
				p.sendMessage(NoPerm);
				
				return true;
		}
			
			if(Insc.contains(p)) {
		
				Insc.remove(p);
				p.sendMessage(Disabled);
		
		return true;
			} else 
				
				Insc.add(p);
				p.sendMessage(Enabled);
				
				return true;
			}
			if(args.length <= 1) {
				
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Usage")));
				
				return true;
			}
			return false;
		}
	{
		{
	}
	{
}
	{
}
	{
}
}
	return false;
}
}