package com.gmail.mmonkey;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ToolSwap extends JavaPlugin{
	
	public static Logger log = Logger.getLogger("Minecraft");
	public HashMap<Player, ToolSwapPlayer> swapList = new HashMap<Player, ToolSwapPlayer>();
	public boolean enable = false;

	public void onEnable() {
		
		long start = System.currentTimeMillis();
		
		getLogger().info("ToolSwap has been Enabled!");
        getServer().getPluginManager().registerEvents(new UseTool(this), this);
        getCommand("toolswap").setExecutor(new Commands(this));
        
        log.info("[ToolSwap] By mmonkey loaded in " + (System.currentTimeMillis() - start) / 1000.0D + " seconds.");
    }
    
    public void onDisable() {
    	getLogger().info("Tool Swap has been Disabled.");
    }
}