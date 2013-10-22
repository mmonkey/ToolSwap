package com.gmail.mmonkey.ToolSwap;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
			
				if(plugin.swapList.containsKey(player)) {
					
					ToolSwapPlayer p = plugin.swapList.get(player);
					
					if(p.getSwap()){
						
						ItemStack itemInHand = player.getItemInHand();
						Block clickedBlock = event.getClickedBlock();
						Inventory playerInventory = player.getInventory();
						
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
					plugin.swapList.put(player, new ToolSwapPlayer(player, plugin.enable));
				}
			}
		}

		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			final Player player = event.getPlayer();
			
			if(player.hasPermission("toolswap.use")) {
			
				if(plugin.swapList.containsKey(player)) {
					
					ToolSwapPlayer p = plugin.swapList.get(player);
					
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
					plugin.swapList.put(player, new ToolSwapPlayer(player, plugin.enable));
				}
			}
		}
	}
	
	@EventHandler
	public void onMobHit(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player) {
			
			Player player = (Player) event.getDamager();
			
			if(player.hasPermission("toolswap.use")) {
			
				if(plugin.swapList.containsKey(player)){
					
					ToolSwapPlayer p = plugin.swapList.get(player);
					
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
					plugin.swapList.put(player, new ToolSwapPlayer(player, plugin.enable));
				}
			}
		}
	}
	
	//Test block
	private boolean isAxeBlock(Material block){
		return plugin.configAxeBlocks.contains(block);
	}
	private boolean isPickaxeBlock(Material block){
		return plugin.configPickaxeBlocks.contains(block);
	}
	private boolean isStoneOrBetterBlock(Material block){
		boolean result = false;
		if(plugin.configPickaxeBlocks.contains(block) && Arrays.asList(plugin.pickaxeStoneBlocks).contains(block)){
			result = true;
		}
		return result;
	}
	private boolean isIronOrBetterBlock(Material block){
		boolean result = false;
		if(plugin.configPickaxeBlocks.contains(block) && Arrays.asList(plugin.pickaxeIronBlocks).contains(block)){
			result = true;
		}
		return result;
	}
	private boolean isDiamondOrBetterBlock(Material block){
		boolean result = false;
		if(plugin.configPickaxeBlocks.contains(block) && Arrays.asList(plugin.pickaxeDiamondBlocks).contains(block)){
			result = true;
		}
		return result;
	}
	private boolean isShearBlock(Material block){
		return plugin.configShearBlocks.contains(block);
	}
	private boolean isShovelBlock(Material block){
		return plugin.configShovelBlocks.contains(block);
	}
	private boolean isSwordBlock(Material block){
		return plugin.configSwordBlocks.contains(block);
	}
	private boolean isTorchBlock(Material block){
		return Arrays.asList(plugin.torchBlocks).contains(block);
	}
	
	//Test tool
	private boolean isAxe(Material block){
		return Arrays.asList(plugin.axes).contains(block);
	}
	private boolean isPickaxe(Material block){
		return Arrays.asList(plugin.pickaxes).contains(block);
	}
	private boolean isStoneOrBetter(Material block){
		return Arrays.asList(plugin.stoneOrBetter).contains(block);
	}
	private boolean isIronOrBetter(Material block){
		return Arrays.asList(plugin.ironOrBetter).contains(block);
	}
	private boolean isDiamondOrBetter(Material block){
		return Arrays.asList(plugin.diamondOrBetter).contains(block);
	}
	private boolean isShears(Material block){
		return Arrays.asList(plugin.shears).contains(block);
	}
	private boolean isShovel(Material block){
		return Arrays.asList(plugin.shovels).contains(block);
	}
	private boolean isSword(Material block){
		return Arrays.asList(plugin.swords).contains(block);
	}
	private boolean isBow(Material block){
		return block.equals(Material.BOW);
	}
	
	//Test Entity
	private boolean isMonster(EntityType entity){
		return plugin.configEnemies.contains(entity);
	}
	private boolean isPlayer(EntityType entity){
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
}