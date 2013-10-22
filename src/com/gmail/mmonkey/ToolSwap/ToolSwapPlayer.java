package com.gmail.mmonkey.ToolSwap;

import org.bukkit.entity.Player;

public class ToolSwapPlayer {
	
	Player player;
	boolean swap;
	
	public ToolSwapPlayer(Player player, boolean enabled){
		this.player = player;
		this.swap = enabled;
	}
	public void setSwap(boolean enabled){
		this.swap = enabled;
	}
	public boolean getSwap(){
		return this.swap;
	}
	public Player getPlayer(){
		return this.player;
	}
}