package com.gmail.mmonkey.ToolSwap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UseTool implements Listener {
	
	private ToolSwap plugin;

	public UseTool(ToolSwap plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onHit(PlayerInteractEvent event){
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			Player player = event.getPlayer();
			
			if(player.hasPermission("toolswap.use")) {
				
				if(player.getGameMode() != GameMode.CREATIVE) {
			
					if(plugin.swapList.containsKey(player.getName())) {
						
						final ToolSwapPlayer p = plugin.swapList.get(player.getName());
						
						if(!p.getListening()) {
							
							if(p.getSwap()){
								
								ItemStack itemInHand = player.getItemInHand();
								Block clickedBlock = event.getClickedBlock();
								Inventory playerInventory = player.getInventory();
								
								if(isPreferredBlock(player.getName(), clickedBlock.getType())){
									Material tool = p.getPreferredItem(clickedBlock.getType());
									for(Entry<Integer, ? extends ItemStack> tools: playerInventory.all(tool).entrySet()) {
										if(p.getEnchantments(clickedBlock.getType(), p.getPreferredItem(clickedBlock.getType())) != null) {
											if(isEnchantmentEqual(tools.getValue().getEnchantments(), p.getEnchantments(clickedBlock.getType(), p.getPreferredItem(clickedBlock.getType())))) {
												int index = -1;
												if(tools.getKey() <= 8) {
													index = player.getInventory().getHeldItemSlot();
												}
												if(!p.getToolFlag()) {
													if(index != -1) {
														p.setToolFlag(true, index);
													} else {
														p.setToolFlag(true, tools.getKey());
													}
												}
												swapItem(player, tools.getKey());
												return;
											}
										} else {
											
											if(tools.getValue().getEnchantments().size() == 0) {
												int index = -1;
												if(tools.getKey() <= 8) {
													index = player.getInventory().getHeldItemSlot();
												}
												if(!p.getToolFlag()) {
													if(index != -1) {
														p.setToolFlag(true, index);
													} else {
														p.setToolFlag(true, tools.getKey());
													}
												}
												swapItem(player, tools.getKey());
												return;
											}
										}
									}
								} else {
									
									if(p.getToolFlag()) {
										swapItem(player, p.getToolIndex());
										p.setToolFlag(false, -1);
									}
									
								}
								
								if(!isBow(itemInHand.getType())) {
								
									if(isAxeBlock(clickedBlock.getType()) && (!isAxe(itemInHand.getType()))) {
										int min = -1;
										int temp = -1;
										for(int i = 0; i < plugin.axes.length; i++) {
											if(playerInventory.contains(plugin.axes[i])) {
												temp = playerInventory.first(plugin.axes[i]);
												if(min == -1 || temp < min) {
													min = temp;
												}
											}
										}
										if(min != -1) {
											swapItem(player, min);
										}
										return;
									}
									if(isPickaxeBlock(clickedBlock.getType()) && (!isPickaxe(itemInHand.getType()))) {
										int min = -1;
										int temp = -1;
										for(int i = 0; i < plugin.pickaxes.length; i++) {
											if(playerInventory.contains(plugin.pickaxes[i])) {
												temp = playerInventory.first(plugin.pickaxes[i]);
												if(min == -1 || temp < min) {
													min = temp;
												}
											}
										}
										if(min != -1) {
											swapItem(player, min);
										}
										return;
									}
								
									if(isStoneOrBetterBlock(clickedBlock.getType()) && (!isStoneOrBetter(itemInHand.getType()))) {
										int min = -1;
										int temp = -1;
										for(int i = 0; i < plugin.stoneOrBetter.length; i++) {
											if(playerInventory.contains(plugin.stoneOrBetter[i])) {
												temp = playerInventory.first(plugin.stoneOrBetter[i]);
												if(min == -1 || temp < min) {
													min = temp;
												}
											}
										}
										if(min != -1) {
											swapItem(player, min);
										}
										return;
									}
									
									if(isIronOrBetterBlock(clickedBlock.getType()) && (!isIronOrBetter(itemInHand.getType()))){
										int min = -1;
										int temp = -1;
										for(int i = 0; i < plugin.ironOrBetter.length; i++) {
											if(playerInventory.contains(plugin.ironOrBetter[i])) {
												temp = playerInventory.first(plugin.ironOrBetter[i]);
												if(min == -1 || temp < min) {
													min = temp;
												}
											}
										}
										if(min != -1) {
											swapItem(player, min);
										}
										return;
									}
									
									if(isDiamondOrBetterBlock(clickedBlock.getType()) && (!isDiamondOrBetter(itemInHand.getType()))){
										int min = -1;
										int temp = -1;
										for(int i = 0; i < plugin.diamondOrBetter.length; i++) {
											if(playerInventory.contains(plugin.diamondOrBetter[i])) {
												temp = playerInventory.first(plugin.diamondOrBetter[i]);
												if(min == -1 || temp < min) {
													min = temp;
												}
											}
										}
										if(min != -1) {
											swapItem(player, min);
										}
										return;
									}
									
									if(isShearBlock(clickedBlock.getType()) && (!isShears(itemInHand.getType()))) {
										int min = -1;
										int temp = -1;
										for(int i = 0; i < plugin.shears.length; i++){
											if(playerInventory.contains(plugin.shears[i])){
												temp = playerInventory.first(plugin.shears[i]);
												if(min == -1 || temp < min) {
													min = temp;
												}
											}
										}
										if(min != -1) {
											swapItem(player, min);
										}
										return;
									}
									
									if(isShovelBlock(clickedBlock.getType()) && (!isShovel(itemInHand.getType()))) {
										int min = -1;
										int temp = -1;
										for(int i = 0; i < plugin.shovels.length; i++) {
											if(playerInventory.contains(plugin.shovels[i])) {
												temp = playerInventory.first(plugin.shovels[i]);
												if(min == -1 || temp < min) {
													min = temp;
												}
											}
										}
										if(min != -1) {
											swapItem(player, min);
										}
										return;
									}
									
									if(isSwordBlock(clickedBlock.getType()) && (!isSword(itemInHand.getType()))) {
										int min = -1;
										int temp = -1;
										for(int i = 0; i < plugin.swords.length; i++) {
											if(playerInventory.contains(plugin.swords[i])) {
												temp = playerInventory.first(plugin.swords[i]);
												if(min == -1 || temp < min) {
													min = temp;
												}
											}
										}
										if(min != -1) {
											swapItem(player, min);
										}
										return;
									}
								}
							}
							
						} else {
						
							ItemStack itemInHand = player.getItemInHand();
							Block clickedBlock = event.getClickedBlock();
							
							if(isAxe(itemInHand.getType()) || isPickaxe(itemInHand.getType()) || isStoneOrBetter(itemInHand.getType()) || isIronOrBetter(itemInHand.getType()) || isDiamondOrBetter(itemInHand.getType()) || isShears(itemInHand.getType())|| isShovel(itemInHand.getType())|| isSword(itemInHand.getType())) {
								if(isAxeBlock(clickedBlock.getType()) || isPickaxeBlock(clickedBlock.getType()) || isStoneOrBetterBlock(clickedBlock.getType()) || isIronOrBetterBlock(clickedBlock.getType()) || isDiamondOrBetterBlock(clickedBlock.getType()) || isShearBlock(clickedBlock.getType()) || isShovelBlock(clickedBlock.getType()) || isSwordBlock(clickedBlock.getType())) {
									
									
									
									for(final HashMap<Material, HashMap<Material, HashMap<String, Integer>>> item: p.preferenceList) {
										if(item.containsKey(clickedBlock.getType())) {
											this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
												public void run() {
													p.preferenceList.remove(item);
												}
											}, 1L);
										}
									}
									
									if(itemInHand.getEnchantments().isEmpty()) {
										p.addToPreferenceList(clickedBlock.getType(), itemInHand.getType());
									} else {
										p.addToPreferenceList(clickedBlock.getType(), itemInHand.getType(), itemInHand.getEnchantments());
									}
									
									if(!plugin.players.contains(p.getPlayer())){
										plugin.players.add(p.getPlayer());
									}
									
									p.setListening(false);
									player.sendMessage("[" + ChatColor.GREEN + "ToolSwap" + ChatColor.WHITE + "]" + ChatColor.YELLOW + " Tool preference has been saved for block type " + clickedBlock.getType().toString() + ".");
									
								}
							} else {
								
								player.sendMessage("[" + ChatColor.GREEN + "ToolSwap" + ChatColor.WHITE + "]" + ChatColor.RED + itemInHand.getType().toString() + " cannot be saved as a preferred tool. Please try again or to cancel, use command:" + ChatColor.WHITE + " /toolswap cancel");
								
							}
						}
						
					} else {
						plugin.swapList.put(player.getName(), new ToolSwapPlayer(player.getName(), plugin.enable));
					} //swapList check
				} //Game mode check
			} //Permission check
		} //Action check

		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			final Player player = event.getPlayer();
			
			if(player.hasPermission("toolswap.use")) {
				
				if(player.getGameMode() != GameMode.CREATIVE) {
			
					if(plugin.swapList.containsKey(player.getName())) {
						
						ToolSwapPlayer p = plugin.swapList.get(player.getName());
						
						if(p.getSwap() && plugin.torchSwapping){
						
							ItemStack itemInHand = player.getItemInHand();
							Block clickedBlock = event.getClickedBlock();
							final Inventory playerInventory = player.getInventory();
								
							if(isTorchBlock(clickedBlock.getType()) && (isPickaxe(itemInHand.getType()))) {
								if(playerInventory.contains(plugin.torch)) {
									this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
										public void run() {
											swapItem(player, playerInventory.first(plugin.torch));
										}
									}, 1L); //delays the swap by 1 tick
									return;
								}
								return;
							}
							if(isTorchBlock(clickedBlock.getType()) && (isShovel(itemInHand.getType()))) {
								if(playerInventory.contains(plugin.torch)) {
									this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
										public void run() {
											swapItem(player, playerInventory.first(plugin.torch));
										}
									}, 1L); //delays the swap by 1 tick
									return;
								}
								return;
							}
						}
					} else {
						plugin.swapList.put(player.getName(), new ToolSwapPlayer(player.getName(), plugin.enable));
					} //swapList check
				} //Game mode check
			} //Permission check
		} //Action check
	} //Event
	
	@EventHandler
	public void onMobHit(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player) {
			
			Player player = (Player) event.getDamager();
			
			if(player.hasPermission("toolswap.use")) {
			
				if(plugin.swapList.containsKey(player.getName())){
					
					ToolSwapPlayer p = plugin.swapList.get(player.getName());
					
					if(p.getSwap()){
						
						ItemStack itemInHand = player.getItemInHand();
						EntityType entity = event.getEntityType();
						Inventory playerInventory = player.getInventory();
						
						if(isMonster(entity) && (!isSword(itemInHand.getType()))){
							int min = -1;
							int temp = -1;
							for(int i = 0; i < plugin.swords.length; i++) {
								if(playerInventory.contains(plugin.swords[i])) {
									temp = playerInventory.first(plugin.swords[i]);
									if(min == -1 || temp < min) {
										min = temp;
									}
								}
							}
							if(min != -1) {
								swapItem(player, min);
							}
							return;
						}
					}
					if(p.getSwap() && plugin.onPlayer){
						
						ItemStack itemInHand = player.getItemInHand();
						EntityType entity = event.getEntityType();
						Inventory playerInventory = player.getInventory();
						
						if(isPlayer(entity) && (!isSword(itemInHand.getType()))){
							int min = -1;
							int temp = -1;
							for(int i = 0; i < plugin.swords.length; i++) {
								if(playerInventory.contains(plugin.swords[i])) {
									temp = playerInventory.first(plugin.swords[i]);
									if(min == -1 || temp < min) {
										min = temp;
									}
								}
							}
							if(min != -1) {
								swapItem(player, min);
							}
							return;
						}
					}
				} else {
					plugin.swapList.put(player.getName(), new ToolSwapPlayer(player.getName(), plugin.enable));
				}
			}
		}
	}
	
	//Test block
	private boolean isPreferredBlock(String playerName, Material block) {
		return plugin.swapList.get(playerName).getPreferences().containsKey(block);
	}
	private boolean isAxeBlock(Material block) {
		return plugin.configAxeBlocks.contains(block);
	}
	private boolean isPickaxeBlock(Material block) {
		return plugin.configPickaxeBlocks.contains(block);
	}
	private boolean isStoneOrBetterBlock(Material block) {
		boolean result = false;
		if(plugin.configPickaxeBlocks.contains(block) && Arrays.asList(plugin.pickaxeStoneBlocks).contains(block)){
			result = true;
		}
		return result;
	}
	private boolean isIronOrBetterBlock(Material block) {
		boolean result = false;
		if(plugin.configPickaxeBlocks.contains(block) && Arrays.asList(plugin.pickaxeIronBlocks).contains(block)){
			result = true;
		}
		return result;
	}
	private boolean isDiamondOrBetterBlock(Material block) {
		boolean result = false;
		if(plugin.configPickaxeBlocks.contains(block) && Arrays.asList(plugin.pickaxeDiamondBlocks).contains(block)){
			result = true;
		}
		return result;
	}
	private boolean isShearBlock(Material block) {
		return plugin.configShearBlocks.contains(block);
	}
	private boolean isShovelBlock(Material block) {
		return plugin.configShovelBlocks.contains(block);
	}
	private boolean isSwordBlock(Material block) {
		return plugin.configSwordBlocks.contains(block);
	}
	private boolean isTorchBlock(Material block) {
		return Arrays.asList(plugin.torchBlocks).contains(block);
	}
	
	//Test tool
	private boolean isAxe(Material block) {
		return Arrays.asList(plugin.axes).contains(block);
	}
	private boolean isPickaxe(Material block) {
		return Arrays.asList(plugin.pickaxes).contains(block);
	}
	private boolean isStoneOrBetter(Material block) {
		return Arrays.asList(plugin.stoneOrBetter).contains(block);
	}
	private boolean isIronOrBetter(Material block) {
		return Arrays.asList(plugin.ironOrBetter).contains(block);
	}
	private boolean isDiamondOrBetter(Material block) {
		return Arrays.asList(plugin.diamondOrBetter).contains(block);
	}
	private boolean isShears(Material block) {
		return Arrays.asList(plugin.shears).contains(block);
	}
	private boolean isShovel(Material block) {
		return Arrays.asList(plugin.shovels).contains(block);
	}
	private boolean isSword(Material block) {
		return Arrays.asList(plugin.swords).contains(block);
	}
	private boolean isBow(Material block) {
		return block.equals(Material.BOW);
	}
	
	//Test Entity
	private boolean isMonster(EntityType entity) {
		return plugin.configEnemies.contains(entity);
	}
	private boolean isPlayer(EntityType entity) {
		return entity.equals(EntityType.PLAYER);
	}
	
	//SwapItem
	public void swapItem(Player player, int index) {
		Inventory playerInventory = player.getInventory();
		
		if(index <= 8){
			player.getInventory().setHeldItemSlot(index);
		} else {
			if (player.getItemInHand().getType() == Material.AIR) {
				player.setItemInHand(playerInventory.getItem(index));
				playerInventory.setItem(index, null);
				return;
				
			} else {
				ItemStack temp = playerInventory.getItem(index);
				playerInventory.setItem(index, player.getItemInHand());
				player.setItemInHand(temp);
				return;
			}
		}
	}
	
	//Test Enchantment Maps for Equality
	public boolean isEnchantmentEqual(Map<Enchantment, Integer> enchantment1, Map<Enchantment, Integer> enchantment2) {
		if (enchantment1.size() != enchantment2.size()) {
			return false;
		}
		for (Enchantment key: enchantment1.keySet()) {
			if (!enchantment1.get(key).equals(enchantment2.get(key))) {
				return false;
			}
		}
		return true;
	}
	
	@EventHandler
	public void onLogout(PlayerQuitEvent event) {
		if(plugin.players.contains(event.getPlayer().getName())) {
			ToolSwapPlayer p = plugin.swapList.get(event.getPlayer().getName());
			
			if(p.getListening()) {
				p.setListening(false);
			}
			
			if(p.getToolFlag()) {
				swapItem(event.getPlayer(), p.getToolIndex());
				p.setToolFlag(false, -1);
			}
		}
	}
}