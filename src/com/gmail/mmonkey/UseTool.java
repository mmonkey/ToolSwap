package com.gmail.mmonkey;

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

	private static final Material[] axes = {Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE};
	private static final Material[] pickaxes = {Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};
	private static final Material[] stoneOrBetter = {Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};
	private static final Material[] ironOrBetter = {Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};
	private static final Material[] diamondOrBetter = {Material.DIAMOND_PICKAXE};
	private static final Material[] shears = {Material.SHEARS};
	private static final Material[] shovels = {Material.WOOD_SPADE, Material.STONE_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE};
	private static final Material[] swords = {Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD};
	private static final Material[] axeBlocks = {Material.LOG, Material.WOOD, Material.WOOD_DOUBLE_STEP, Material.WOOD_STEP, Material.WOOD_STAIRS, Material.WORKBENCH, Material.BIRCH_WOOD_STAIRS, Material.JUNGLE_WOOD_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.LADDER, Material.RAILS, Material.ACTIVATOR_RAIL, Material.DETECTOR_RAIL, Material.POWERED_RAIL, Material.SIGN, Material.SIGN_POST, Material.PUMPKIN, Material.JACK_O_LANTERN, Material.HUGE_MUSHROOM_1, Material.HUGE_MUSHROOM_2, Material.FENCE, Material.FENCE_GATE};
	private static final Material[] pickaxeBlocks = {Material.STONE, Material.COBBLESTONE, Material.COAL_ORE, Material.DISPENSER, Material.SANDSTONE, Material.DOUBLE_STEP, Material.STEP, Material.COBBLESTONE_STAIRS, Material.COBBLE_WALL, Material.BRICK, Material.BRICK_STAIRS, Material.FURNACE, Material.BREWING_STAND, Material.MOSSY_COBBLESTONE, Material.CAULDRON, Material.ICE, Material.NETHERRACK, Material.NETHER_BRICK, Material.NETHER_BRICK_STAIRS, Material.NETHER_FENCE, Material.ANVIL, Material.QUARTZ_ORE, Material.QUARTZ_BLOCK, Material.QUARTZ_STAIRS, Material.CLAY_BRICK, Material.STAINED_CLAY, Material.HARD_CLAY, Material.HOPPER};
	private static final Material[] pickaxeStoneBlocks = {Material.IRON_ORE, Material.IRON_BLOCK, Material.LAPIS_ORE, Material.LAPIS_BLOCK};
	private static final Material[] pickaxeIronBlocks = {Material.GOLD_ORE, Material.GOLD_BLOCK, Material.REDSTONE_ORE, Material.GLOWING_REDSTONE_ORE, Material.REDSTONE_BLOCK, Material.EMERALD_ORE, Material.EMERALD_BLOCK, Material.DIAMOND_ORE, Material.DIAMOND_BLOCK};
	private static final Material[] pickaxeDiamondBlocks = {Material.OBSIDIAN};
	private static final Material[] shearBlocks = {Material.WOOL, Material.LEAVES};
	private static final Material[] shovelBlocks = {Material.GRASS, Material.DIRT, Material.SAND, Material.GRAVEL, Material.SNOW, Material.SNOW_BLOCK, Material.CLAY, Material.SOUL_SAND, Material.MYCEL};
	private static final Material[] swordBlocks = {Material.MELON_BLOCK, Material.WEB};
	private static final Material torch = Material.TORCH;
	private static final Material[] torchBlocks = {Material.LOG, Material.WOOD, Material.WOOD_DOUBLE_STEP, Material.WOOD_STEP, Material.PUMPKIN, Material.JACK_O_LANTERN, Material.HUGE_MUSHROOM_1, Material.HUGE_MUSHROOM_2, Material.FENCE, Material.STONE, Material.COBBLESTONE, Material.COAL_ORE, Material.SANDSTONE, Material.DOUBLE_STEP, Material.STEP, Material.COBBLE_WALL, Material.BRICK, Material.MOSSY_COBBLESTONE, Material.ICE, Material.NETHERRACK, Material.NETHER_BRICK, Material.NETHER_FENCE, Material.QUARTZ_ORE, Material.QUARTZ_BLOCK, Material.CLAY_BRICK, Material.STAINED_CLAY, Material.HARD_CLAY, Material.IRON_ORE, Material.IRON_BLOCK, Material.LAPIS_ORE, Material.LAPIS_BLOCK, Material.GOLD_ORE, Material.GOLD_BLOCK, Material.REDSTONE_ORE, Material.GLOWING_REDSTONE_ORE, Material.REDSTONE_BLOCK, Material.EMERALD_ORE, Material.EMERALD_BLOCK, Material.DIAMOND_ORE, Material.DIAMOND_BLOCK, Material.OBSIDIAN, Material.WOOL, Material.LEAVES, Material.GRASS, Material.DIRT, Material.SAND, Material.GRAVEL, Material.SNOW, Material.SNOW_BLOCK, Material.CLAY, Material.SOUL_SAND, Material.MYCEL, Material.MELON_BLOCK};
	
	private static final EntityType[] monsters = {EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.ENDER_DRAGON, EntityType.ENDERMAN, EntityType.GHAST, EntityType.IRON_GOLEM, EntityType.MAGMA_CUBE, EntityType.PIG_ZOMBIE, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.WITCH, EntityType.ZOMBIE};
	
	@EventHandler
	public void onHit(PlayerInteractEvent event){
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			Player player = event.getPlayer();
			
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
							for(int i = 0; i < axes.length; i++) {
								if(playerInventory.contains(axes[i])) {
									temp = playerInventory.first(axes[i]);
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
							for(int i = 0; i < pickaxes.length; i++) {
								if(playerInventory.contains(pickaxes[i])) {
									temp = playerInventory.first(pickaxes[i]);
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
							for(int i = 0; i < stoneOrBetter.length; i++) {
								if(playerInventory.contains(stoneOrBetter[i])) {
									temp = playerInventory.first(stoneOrBetter[i]);
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
							for(int i = 0; i < ironOrBetter.length; i++) {
								if(playerInventory.contains(ironOrBetter[i])) {
									temp = playerInventory.first(ironOrBetter[i]);
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
							for(int i = 0; i < diamondOrBetter.length; i++) {
								if(playerInventory.contains(diamondOrBetter[i])) {
									temp = playerInventory.first(diamondOrBetter[i]);
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
							for(int i = 0; i < shears.length; i++){
								if(playerInventory.contains(shears[i])){
									temp = playerInventory.first(shears[i]);
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
							for(int i = 0; i < shovels.length; i++) {
								if(playerInventory.contains(shovels[i])) {
									temp = playerInventory.first(shovels[i]);
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
							for(int i = 0; i < swords.length; i++) {
								if(playerInventory.contains(swords[i])) {
									temp = playerInventory.first(swords[i]);
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

		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			final Player player = event.getPlayer();
			
			if(plugin.swapList.containsKey(player)) {
				
				ToolSwapPlayer p = plugin.swapList.get(player);
				
				if(p.getSwap()){
				
					ItemStack itemInHand = player.getItemInHand();
					Block clickedBlock = event.getClickedBlock();
					final Inventory playerInventory = player.getInventory();
						
					if(isTorchBlock(clickedBlock.getType()) && (isPickaxe(itemInHand.getType()))) {
						if(playerInventory.contains(torch)) {
							this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
								public void run() {
									swapItem(player, playerInventory.first(torch));
								}
							}, 1L); //delays the swap by 1 tick
							return;
						}
						return;
					}
					if(isTorchBlock(clickedBlock.getType()) && (isShovel(itemInHand.getType()))) {
						if(playerInventory.contains(torch)) {
							this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
								public void run() {
									swapItem(player, playerInventory.first(torch));
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
	
	@EventHandler
	public void onMobHit(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player) {
			
			Player player = (Player) event.getDamager();
			
			if(plugin.swapList.containsKey(player)){
				
				ToolSwapPlayer p = plugin.swapList.get(player);
				
				if(p.getSwap()){
					
					ItemStack itemInHand = player.getItemInHand();
					EntityType entity = event.getEntityType();
					Inventory playerInventory = player.getInventory();
					
					if(isMonster(entity) && (!isSword(itemInHand.getType()))){
						int min = -1;
						int temp = -1;
						for(int i = 0; i < swords.length; i++) {
							if(playerInventory.contains(swords[i])) {
								temp = playerInventory.first(swords[i]);
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
	
	//Test block
	private boolean isAxeBlock(Material block){
		return Arrays.asList(axeBlocks).contains(block);
	}
	private boolean isPickaxeBlock(Material block){
		return Arrays.asList(pickaxeBlocks).contains(block);
	}
	private boolean isStoneOrBetterBlock(Material block){
		return Arrays.asList(pickaxeStoneBlocks).contains(block);
	}
	private boolean isIronOrBetterBlock(Material block){
		return Arrays.asList(pickaxeIronBlocks).contains(block);
	}
	private boolean isDiamondOrBetterBlock(Material block){
		return Arrays.asList(pickaxeDiamondBlocks).contains(block);
	}
	private boolean isShearBlock(Material block){
		return Arrays.asList(shearBlocks).contains(block);
	}
	private boolean isShovelBlock(Material block){
		return Arrays.asList(shovelBlocks).contains(block);
	}
	private boolean isSwordBlock(Material block){
		return Arrays.asList(swordBlocks).contains(block);
	}
	private boolean isTorchBlock(Material block){
		return Arrays.asList(torchBlocks).contains(block);
	}
	
	//Test tool
	private boolean isAxe(Material block){
		return Arrays.asList(axes).contains(block);
	}
	private boolean isPickaxe(Material block){
		return Arrays.asList(pickaxes).contains(block);
	}
	private boolean isStoneOrBetter(Material block){
		return Arrays.asList(stoneOrBetter).contains(block);
	}
	private boolean isIronOrBetter(Material block){
		return Arrays.asList(ironOrBetter).contains(block);
	}
	private boolean isDiamondOrBetter(Material block){
		return Arrays.asList(diamondOrBetter).contains(block);
	}
	private boolean isShears(Material block){
		return Arrays.asList(shears).contains(block);
	}
	private boolean isShovel(Material block){
		return Arrays.asList(shovels).contains(block);
	}
	private boolean isSword(Material block){
		return Arrays.asList(swords).contains(block);
	}
	private boolean isBow(Material block){
		return block.equals(Material.BOW);
	}
	
	//Test Entity
	private boolean isMonster(EntityType entity){
		return Arrays.asList(monsters).contains(entity);
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