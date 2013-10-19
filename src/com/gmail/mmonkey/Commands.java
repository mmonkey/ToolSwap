package com.gmail.mmonkey;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{
	
	private ToolSwap plugin;
	
	Commands(ToolSwap plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = null;
		
		//Check to see if sender is a player
		if(sender instanceof Player) {
			player = (Player) sender;
		} else {
			sender.sendMessage("Only players may use this command!");
            return true;
		}
		
		//Add player to swapList if not already
		if(!plugin.swapList.containsKey(player)) {
			plugin.swapList.put(player, new ToolSwapPlayer(player, plugin.enable));
		}
		
		//Pull player out of swapList
		ToolSwapPlayer p = plugin.swapList.get(player);
		
		//If command has no arguments, show wether toolswap is enabled or disabled.
		if(args.length == 0) {
			
			if(p.getSwap()) {
				player.sendMessage(ChatColor.GREEN + "ToolSwap is enabled.");
				
			} else {
				player.sendMessage(ChatColor.RED + "ToolSwap is disabled.");
			}
			
		//Turn ToolSwap on or off
		} else if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("on")) {
				if(p.getSwap()){
					player.sendMessage(ChatColor.YELLOW + "ToolSwap is already enabled.");
					
				} else {
					p.setSwap(true);
					player.sendMessage(ChatColor.GREEN + "ToolSwap is enabled.");
				}
				
			} else if(args[0].equalsIgnoreCase("off")) {
				
				if(!p.getSwap()) {
					player.sendMessage(ChatColor.YELLOW + "ToolSwap is already disabled.");
					
				} else {
					p.setSwap(false);
					player.sendMessage(ChatColor.RED + "ToolSwap is now disabled.");
				}
				
			} else {
				player.sendMessage(args[0] + " is not understood.");
			}
			
		}
		
		return true;
	}
}