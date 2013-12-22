package com.gmail.mmonkey.ToolSwap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

public class ToolSwap extends JavaPlugin{
	
	public static Logger log = Logger.getLogger("Minecraft");
	
	//List of ToolSwapPlayers
	public HashMap<String, ToolSwapPlayer> swapList = new HashMap<String, ToolSwapPlayer>();
	
	//Player preferences
	public ArrayList<String> players = new ArrayList<String>();
	public ArrayList<HashMap<String, HashMap<Material, HashMap<Material, HashMap<String, Integer>>>>> playerToolPrefs = new ArrayList<HashMap<String, HashMap<Material, HashMap<Material, HashMap<String, Integer>>>>>();
	
	//Default settings' values
	public boolean enable = false;
	public boolean onPlayer = false;
	public boolean torchSwapping = false;
	
	//List of tools
	public final Material[] axes = {Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE};
	public final Material[] pickaxes = {Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};
	public final Material[] stoneOrBetter = {Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};
	public final Material[] ironOrBetter = {Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};
	public final Material[] diamondOrBetter = {Material.DIAMOND_PICKAXE};
	public final Material[] shears = {Material.SHEARS};
	public final Material[] shovels = {Material.WOOD_SPADE, Material.STONE_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE};
	public final Material[] swords = {Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD};
	public final Material torch = Material.TORCH;
	
	//Materials that require a certain type of pickaxe
	public final Material[] pickaxeStoneBlocks = {Material.IRON_ORE, Material.IRON_BLOCK, Material.LAPIS_ORE, Material.LAPIS_BLOCK};
	public final Material[] pickaxeIronBlocks = {Material.GOLD_ORE, Material.GOLD_BLOCK, Material.REDSTONE_ORE, Material.GLOWING_REDSTONE_ORE, Material.REDSTONE_BLOCK, Material.EMERALD_ORE, Material.EMERALD_BLOCK, Material.DIAMOND_ORE, Material.DIAMOND_BLOCK};
	public final Material[] pickaxeDiamondBlocks = {Material.OBSIDIAN};
	
	//Materials that you may place torches on
	public final Material[] torchBlocks = {Material.LOG, Material.WOOD, Material.WOOD_DOUBLE_STEP, Material.WOOD_STEP, Material.PUMPKIN, Material.JACK_O_LANTERN, Material.HUGE_MUSHROOM_1, Material.HUGE_MUSHROOM_2, Material.FENCE, Material.STONE, Material.COBBLESTONE, Material.COAL_ORE, Material.SANDSTONE, Material.DOUBLE_STEP, Material.STEP, Material.COBBLE_WALL, Material.BRICK, Material.MOSSY_COBBLESTONE, Material.ICE, Material.NETHERRACK, Material.NETHER_BRICK, Material.NETHER_FENCE, Material.QUARTZ_ORE, Material.QUARTZ_BLOCK, Material.CLAY_BRICK, Material.STAINED_CLAY, Material.HARD_CLAY, Material.IRON_ORE, Material.IRON_BLOCK, Material.LAPIS_ORE, Material.LAPIS_BLOCK, Material.GOLD_ORE, Material.GOLD_BLOCK, Material.REDSTONE_ORE, Material.GLOWING_REDSTONE_ORE, Material.REDSTONE_BLOCK, Material.EMERALD_ORE, Material.EMERALD_BLOCK, Material.DIAMOND_ORE, Material.DIAMOND_BLOCK, Material.OBSIDIAN, Material.WOOL, Material.LEAVES, Material.GRASS, Material.DIRT, Material.SAND, Material.GRAVEL, Material.SNOW, Material.SNOW_BLOCK, Material.CLAY, Material.SOUL_SAND, Material.MYCEL, Material.MELON_BLOCK};
	
	//Original material lists for each type of tool (Now loaded from config.yml file)
	//public final Material[] axeBlocks = {Material.LOG, Material.WOOD, Material.WOOD_DOUBLE_STEP, Material.WOOD_STEP, Material.WOOD_STAIRS, Material.WORKBENCH, Material.BIRCH_WOOD_STAIRS, Material.JUNGLE_WOOD_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.LADDER, Material.RAILS, Material.ACTIVATOR_RAIL, Material.DETECTOR_RAIL, Material.POWERED_RAIL, Material.SIGN, Material.SIGN_POST, Material.PUMPKIN, Material.JACK_O_LANTERN, Material.HUGE_MUSHROOM_1, Material.HUGE_MUSHROOM_2, Material.FENCE, Material.FENCE_GATE};
	//public final Material[] pickaxeBlocks = {Material.STONE, Material.COBBLESTONE, Material.COAL_ORE, Material.DISPENSER, Material.SANDSTONE, Material.DOUBLE_STEP, Material.STEP, Material.COBBLESTONE_STAIRS, Material.COBBLE_WALL, Material.BRICK, Material.BRICK_STAIRS, Material.FURNACE, Material.BREWING_STAND, Material.MOSSY_COBBLESTONE, Material.CAULDRON, Material.ICE, Material.NETHERRACK, Material.NETHER_BRICK, Material.NETHER_BRICK_STAIRS, Material.NETHER_FENCE, Material.ANVIL, Material.QUARTZ_ORE, Material.QUARTZ_BLOCK, Material.QUARTZ_STAIRS, Material.CLAY_BRICK, Material.STAINED_CLAY, Material.HARD_CLAY, Material.HOPPER};
	//public final Material[] shearBlocks = {Material.WOOL, Material.LEAVES};
	//public final Material[] shovelBlocks = {Material.GRASS, Material.DIRT, Material.SAND, Material.GRAVEL, Material.SNOW, Material.SNOW_BLOCK, Material.CLAY, Material.SOUL_SAND, Material.MYCEL};
	//public final Material[] swordBlocks = {Material.MELON_BLOCK, Material.WEB};
	//public final EntityType[] enemies = {EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.ENDER_DRAGON, EntityType.ENDERMAN, EntityType.GHAST, EntityType.IRON_GOLEM, EntityType.MAGMA_CUBE, EntityType.PIG_ZOMBIE, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.WITCH, EntityType.ZOMBIE};
	
	//ArrayLists to be populated by the config.yml file
	public ArrayList<Material> configAxeBlocks = new ArrayList<Material>();
	public ArrayList<Material> configPickaxeBlocks = new ArrayList<Material>();
	public ArrayList<Material> configShearBlocks = new ArrayList<Material>();
	public ArrayList<Material> configShovelBlocks = new ArrayList<Material>();
	public ArrayList<Material> configSwordBlocks = new ArrayList<Material>();
	public ArrayList<EntityType> configEnemies = new ArrayList<EntityType>();
	
	public void onEnable() {
		
		long start = System.currentTimeMillis();
		
		load();
		
		getLogger().info("ToolSwap has been Enabled!");
        getServer().getPluginManager().registerEvents(new UseTool(this), this);
        getCommand("toolswap").setExecutor(new Commands(this));
        
        log.info("[ToolSwap] By mmonkey loaded in " + (System.currentTimeMillis() - start) / 1000.0D + " seconds.");
    }
    
	public void load(){
		
		//If config.yml does not exist, create a new default one
		this.saveDefaultConfig();
		
		//Load config.yml
		this.enable = this.getConfig().getBoolean("general.on-by-default");
		this.torchSwapping = this.getConfig().getBoolean("general.torch-swapping");
		this.onPlayer = this.getConfig().getBoolean("general.swap-sword-on-player");
		
		//Populate materials for each tool from config.yml
		List<String> loadAxeBlocks = ToolSwap.this.getConfig().getStringList("blocks.axe-blocks");
		List<String> loadPickaxeBlocks = ToolSwap.this.getConfig().getStringList("blocks.pickaxe-blocks");
		List<String> loadShearBlocks = ToolSwap.this.getConfig().getStringList("blocks.shear-blocks");
		List<String> loadShovelBlocks = ToolSwap.this.getConfig().getStringList("blocks.shovel-blocks");
		List<String> loadSwordBlocks = ToolSwap.this.getConfig().getStringList("blocks.sword-blocks");
		List<String> loadEnemies = ToolSwap.this.getConfig().getStringList("mobs.enemies");
		
		//Convert string list to materials list
		for(String s: loadAxeBlocks) {
			if(Material.getMaterial(s) != null){
				configAxeBlocks.add(Material.getMaterial(s));
			}
		}
		for(String s: loadPickaxeBlocks) {
			if(Material.getMaterial(s) != null){
				configPickaxeBlocks.add(Material.getMaterial(s));
			}
		}
		for(String s: loadShearBlocks) {
			if(Material.getMaterial(s) != null){
				configShearBlocks.add(Material.getMaterial(s));
			}
		}
		for(String s: loadShovelBlocks) {
			if(Material.getMaterial(s) != null){
				configShovelBlocks.add(Material.getMaterial(s));
			}
		}
		for(String s: loadSwordBlocks) {
			if(Material.getMaterial(s) != null){
				configSwordBlocks.add(Material.getMaterial(s));
			}
		}
		for(String s: loadEnemies) {
			if(EntityType.valueOf(s) != null){
				configEnemies.add(EntityType.valueOf(s));
			}
		}
		
		//Import players who have preferences
		try{
			players = SaveLoad.load("plugins\\ToolSwap\\players.bin");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Import preferences
		try{
			playerToolPrefs = SaveLoad.load("plugins\\ToolSwap\\prefs.bin");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Distribute preferences to ToolSwapPlayers
		if(!players.isEmpty() && !playerToolPrefs.isEmpty()) {
			
			for(int i = 0; i < players.size(); i++) {
				ToolSwapPlayer p = new ToolSwapPlayer(players.get(i), enable);
				for(int j = 0; j < playerToolPrefs.size(); j++) {
					if(playerToolPrefs.get(j).containsKey(p.getPlayer())) {
						for(Map.Entry<Material, HashMap<Material, HashMap<String, Integer>>> blocks: playerToolPrefs.get(j).get(p.getPlayer()).entrySet()) {
							for(Map.Entry<Material, HashMap<String, Integer>> tools: blocks.getValue().entrySet()) {
								
								HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
								
								for(Map.Entry<String, Integer> enchants: tools.getValue().entrySet()) {
									if(!enchants.getKey().equals("NOT_ENCHANTED")) {
										enchantments.put(Enchantment.getByName(enchants.getKey()), enchants.getValue());
									}
								}
								
								if(enchantments.isEmpty()) {
									p.addToPreferenceList(blocks.getKey(), tools.getKey());
								
								} else {
									p.addToPreferenceList(blocks.getKey(), tools.getKey(), enchantments);
								}
							}
						}
					}
				}
				if(!swapList.containsKey(players.get(i))) {
					swapList.put(players.get(i), p);
				}
			}
			
		}
		
		//Start metrics
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		
		
	} //end load()
	
	public void unload() {
		//Save players to file
		if(!players.isEmpty()) {
			try{
				SaveLoad.save(players, "plugins\\ToolSwap\\players.bin");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//Save preferences to file
		if(!playerToolPrefs.isEmpty()) {
			try{
				SaveLoad.save(playerToolPrefs, "plugins\\ToolSwap\\prefs.bin");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
    public void onDisable() {
    	savePrefs();
    	unload();
    	getLogger().info("ToolSwap has been Disabled.");
    }
    
    public void savePrefs() {
    	//Clear playerToolPrefs list
    	playerToolPrefs.clear();
    	
    	//Add all players preferences to list
    	for(int i = 0; i < players.size(); i++) {
    		if(swapList.containsKey(players.get(i))) {
    			HashMap<String, HashMap<Material, HashMap<Material, HashMap<String, Integer>>>> preferences = new HashMap<String, HashMap<Material, HashMap<Material, HashMap<String, Integer>>>>();
    			preferences.put(players.get(i), swapList.get(players.get(i)).getPreferences());
    			playerToolPrefs.add(preferences);
    		}
    	}
    }
}