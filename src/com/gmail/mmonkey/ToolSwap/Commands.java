package com.gmail.mmonkey.ToolSwap;

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
		if(!plugin.swapList.containsKey(player.getUniqueId())) {
			plugin.swapList.put(player.getUniqueId(), new ToolSwapPlayer(player.getName(), player.getUniqueId(), plugin.enable));
		}
		
		//Pull player out of swapList
		ToolSwapPlayer p = plugin.swapList.get(player.getUniqueId());
		
		//If command has no arguments, show whether ToolSwap is enabled or disabled.
		if(args.length == 0) {
			
			if(p.getSwap()) {
				player.sendMessage(ChatColor.GREEN + "ToolSwap is enabled.");
				return true;
				
			} else {
				player.sendMessage(ChatColor.RED + "ToolSwap is disabled.");
				return true;
			}
			
		//Turn ToolSwap on
		} else if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("on")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("toolswap.on")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				if(p.getSwap()){
					player.sendMessage(ChatColor.YELLOW + "ToolSwap is already enabled.");
					return true;
					
				} else {
					p.setSwap(true);
					player.sendMessage(ChatColor.GREEN + "ToolSwap is enabled.");
					return true;
				}
			
			//Turn ToolSwap off	
			} else if(args[0].equalsIgnoreCase("off")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("toolswap.off")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				if(!p.getSwap()) {
					player.sendMessage(ChatColor.YELLOW + "ToolSwap is already disabled.");
					return true;
					
				} else {
					p.setSwap(false);
					p.setListening(false);
					player.sendMessage(ChatColor.RED + "ToolSwap is now disabled.");
					return true;
				}
			
			//turn ToolSwap's listening-mode on
			} else if(args[0].equalsIgnoreCase("set")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("Toolswap.pref")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				if(p.getListening()) {
					player.sendMessage(ChatColor.RED + "ToolSwap is already in listening-mode.");
					return true;
					
				} else {
					p.setListening(true);
					player.sendMessage("[" + ChatColor.GREEN + "ToolSwap" + ChatColor.WHITE + "]" + ChatColor.YELLOW + " Listening-Mode enabled.");
					player.sendMessage("[" + ChatColor.GREEN + "ToolSwap" + ChatColor.WHITE + "]" + ChatColor.YELLOW + " To stop listening use command:" + ChatColor.WHITE + " /toolswap cancel");
					return true;
				}
			
			//cancel ToolSwap's listening-mode
			} else if(args[0].equalsIgnoreCase("cancel")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("Toolswap.pref")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				if(!p.getListening()) {
					player.sendMessage(ChatColor.RED + "ToolSwap is not in Listening-Mode, there is nothing to cancel.");
					return true;
				
				} else {
					p.setListening(false);
					player.sendMessage("[" + ChatColor.GREEN + "ToolSwap" + ChatColor.WHITE + "]" + ChatColor.YELLOW + " Listening-Mode disabled.");
					return true;
				}
			
			//list tool preferences for sender	
			} else if(args[0].equalsIgnoreCase("list")) {
			
				//Check to see if player has permission
				if(!player.hasPermission("Toolswap.pref")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				player.sendMessage("[" + ChatColor.GREEN + "ToolSwap" + ChatColor.WHITE + "]" + ChatColor.YELLOW + " Your preferred tools:");
				
				if(p.getPreferences().getPreferences().size() == 0 || p.getPreferences().getPreferences().isEmpty()) {
					player.sendMessage(ChatColor.RED + "You have no preferred tools.");
					player.sendMessage(ChatColor.YELLOW + "To set a preferred tool:"+ ChatColor.WHITE + " /toolswap set");
				} else {
					for(int i = 0; i < p.getPreferences().print().size(); i++) {
						player.sendMessage(Integer.toString((i + 1)) + " - " + p.getPreferences().print().get(i));
					}
				}
					
				return true;
			
			//Command not understood (invalid arguments)	
			} else {
				player.sendMessage("Command is not understood.");
				return false;
			}
			
		} else if(args.length == 2){
			
			//Delete tool preference
			if(args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("delete")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("Toolswap.pref")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				if(Integer.parseInt(args[1]) > 0) {
					p.getPreferences().remove(Integer.parseInt(args[1]));
					player.sendMessage("[" + ChatColor.GREEN + "ToolSwap" + ChatColor.WHITE + "]" + ChatColor.YELLOW + " Preference " + args[1] + " has been deleted.");
					return true;
				} else {
					player.sendMessage(ChatColor.RED + "Command not understood." + ChatColor.YELLOW + " Usage:" + ChatColor.WHITE + " /toolswap [del/delete] [number]");
					return true;
				}
				
			//Turn ToolSwap on for specified player	
			} else if(args[1].equalsIgnoreCase("on")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("toolswap.player.on")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				@SuppressWarnings("deprecation")
				Player player2 = plugin.getServer().getPlayer(args[0]);
				
				if(player2 != null) {
					
					if(player2.hasPermission("toolswap.use")) {
						
						//Add player to swapList if not already
						if(!plugin.swapList.containsKey(player2.getUniqueId())) {
							plugin.swapList.put(player2.getUniqueId(), new ToolSwapPlayer(player2.getName(), player2.getUniqueId(), plugin.enable));
						}
						
						//Pull player out of swapList
						ToolSwapPlayer p2 = plugin.swapList.get(player2.getUniqueId());
						
						if(p2.getSwap()){
							player.sendMessage(ChatColor.YELLOW + "ToolSwap is already enabled for " + args[0] + ".");
							return true;
							
						} else {
							p2.setSwap(true);
							player.sendMessage(ChatColor.GREEN + "ToolSwap has been enabled for " + args[0] + ".");
							player2.sendMessage(ChatColor.GREEN + "ToolSwap is enabled thanks to " + player.getDisplayName() + ".");
							return true;
						}
						
					} else {
						player.sendMessage("Player " + args[0] + " doesn't have permissions to use ToolSwap!");
						return true;
					}
				
				} else {
					player.sendMessage("Player " + args[0] + " not found.");
					return true;
				}
			
			//Turn ToolSwap off for specified player	
			} else if(args[1].equalsIgnoreCase("off")) {
				
				//Check to see if player has permission
				if(!player.hasPermission("toolswap.player.off")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				@SuppressWarnings("deprecation")
				Player player2 = plugin.getServer().getPlayer(args[0]);
				
				if(player2 != null){
					
					if(player2.hasPermission("toolswap.use")) {
						
						//Add player to swapList if not already
						if(!plugin.swapList.containsKey(player2.getUniqueId())) {
							plugin.swapList.put(player2.getUniqueId(), new ToolSwapPlayer(player2.getName(), player2.getUniqueId(), plugin.enable));
						}
						
						//Pull player out of swapList
						ToolSwapPlayer p2 = plugin.swapList.get(player2.getUniqueId());
						
						if(!p2.getSwap()){
							player.sendMessage(ChatColor.YELLOW + "ToolSwap is already disabled for " + args[0] + ".");
							return true;
							
						} else {
							p2.setSwap(false);
							p2.setListening(false);
							player.sendMessage(ChatColor.GREEN + "ToolSwap has been disabled for " + args[0] + ".");
							player2.sendMessage(ChatColor.GREEN + "ToolSwap has been disabled by " + player.getDisplayName() + ".");
							return true;
						}
					
					} else {
						player.sendMessage("Player " + args[0] + " doesn't have permissions to use ToolSwap!");
						return true;
					}
				
				} else {
					player.sendMessage("Player " + args[0] + " not found.");
					return true;
				}
				
			//Command not understood (wrong number of arguments)	
			} else {
				player.sendMessage(ChatColor.RED + "Command not understood.");
				return false;
			}
		}
		return false;
	}
}