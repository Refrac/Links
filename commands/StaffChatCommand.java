package me.refrac.linkscore.commands;

import java.util.ArrayList;

import me.refrac.linkscore.LinksCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor
{
	public LinksCore plugin;
	
	public static ArrayList<Player> insc = new ArrayList<Player>();
	
	String prefix = ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("Prefix"));
	String enabled = ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("Enabled"));
	String disabled = ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("Disabled"));
	String noPerm = ChatColor.translateAlternateColorCodes('&', prefix + LinksCore.plugin.getConfig().getString("NoPerm"));
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("staffchat"))
		{
			if (!(sender instanceof Player))
			{
				Bukkit.getServer().getLogger().warning(LinksCore.plugin.getConfig().getString("NoConsole"));
				return true;
			}

			Player p = (Player) sender;
		  
			if(args.length == 0) {

				if (!p.hasPermission("staffchat.use")) {
					p.sendMessage(noPerm);

					return true;
				}

				if (insc.contains(p))
				{
					insc.remove(p);
					p.sendMessage(disabled);

					return true;
				}
				else
				{
					insc.add(p);
					p.sendMessage(enabled);

					return true;
				}
			}
			else
			{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', LinksCore.plugin.getConfig().getString("Usage")));
			}
		}
		return true;
	}
}