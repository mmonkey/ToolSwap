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
		
		//If command has no arguments, show whether ToolSwap is enabled or disabled.
		if(args.length == 0) {
			
			if(p.getSwap()) {
				player.sendMessage(ChatColor.GREEN + "ToolSwap is enabled.");
				
			} else {
				player.sendMessage(ChatColor.RED + "ToolSwap is disabled.");
			}
			
		//Turn ToolSwap on or off
		} else if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("on")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("toolswap.on")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				if(p.getSwap()){
					player.sendMessage(ChatColor.YELLOW + "ToolSwap is already enabled.");
					
				} else {
					p.setSwap(true);
					player.sendMessage(ChatColor.GREEN + "ToolSwap is enabled.");
				}
				
			} else if(args[0].equalsIgnoreCase("off")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("toolswap.off")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				if(!p.getSwap()) {
					player.sendMessage(ChatColor.YELLOW + "ToolSwap is already disabled.");
					
				} else {
					p.setSwap(false);
					player.sendMessage(ChatColor.RED + "ToolSwap is now disabled.");
				}
				
			} else {
				player.sendMessage("Command is not understood.");
			}
			
		//Turn ToolSwap on or off for specified player	
		} else if(args.length == 2){
			
			if(args[1].equalsIgnoreCase("on")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("toolswap.player.on")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				Player player2 = plugin.getServer().getPlayer(args[0]);
				
				if(player2 != null){
					//Add player to swapList if not already
					if(!plugin.swapList.containsKey(player2)) {
						plugin.swapList.put(player2, new ToolSwapPlayer(player2, plugin.enable));
					}
					
					//Pull player out of swapList
					ToolSwapPlayer p2 = plugin.swapList.get(player2);
					
					if(p2.getSwap()){
						player.sendMessage(ChatColor.YELLOW + "ToolSwap is already enabled for " + args[0] + ".");
						
					} else {
						p2.setSwap(true);
						player.sendMessage(ChatColor.GREEN + "ToolSwap has been enabled for " + args[0] + ".");
						player2.sendMessage(ChatColor.GREEN + "ToolSwap is enabled thanks to " + player.getDisplayName() + ".");
					}
					
				} else {
					player.sendMessage("Player " + args[0] + " not found.");
				}
				
			} else if(args[1].equalsIgnoreCase("off")) {
				
				//Check to see if player has permission
				if(!p.getPlayer().hasPermission("toolswap.player.off")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				Player player2 = plugin.getServer().getPlayer(args[0]);
				
				if(player2 != null){
					//Add player to swapList if not already
					if(!plugin.swapList.containsKey(player2)) {
						plugin.swapList.put(player2, new ToolSwapPlayer(player2, plugin.enable));
					}
					
					//Pull player out of swapList
					ToolSwapPlayer p2 = plugin.swapList.get(player2);
					
					if(!p2.getSwap()){
						player.sendMessage(ChatColor.YELLOW + "ToolSwap is already disabled for " + args[0] + ".");
						
					} else {
						p2.setSwap(false);
						player.sendMessage(ChatColor.GREEN + "ToolSwap has been disabled for " + args[0] + ".");
						player2.sendMessage(ChatColor.GREEN + "ToolSwap has been disabled by " + player.getDisplayName() + ".");
					}
					
				} else {
					player.sendMessage("Player " + args[0] + " not found.");
				}
			}
		}
		return true;
	}
}