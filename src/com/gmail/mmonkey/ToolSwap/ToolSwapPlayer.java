package com.gmail.mmonkey.ToolSwap;

import java.io.Serializable;
import java.util.UUID;

public class ToolSwapPlayer implements Serializable {
	
	private static final long serialVersionUID = 7403276225537669799L;
	String name;
	UUID playerID;
	PreferenceList preferences = new PreferenceList();
	boolean swap;
	boolean listening = false;
	boolean toolFlag = false;
	int toolIndex;
	
	public ToolSwapPlayer(String name, UUID playerID, boolean enabled) {
		this.name = name;
		this.playerID = playerID;
		this.swap = enabled;
	}
	
	public void setSwap(boolean enabled) {
		this.swap = enabled;
	}
	
	public boolean getSwap() {
		return this.swap;
	}
	
	public void setListening(boolean listening) {
		this.listening = listening;
	}
	
	public boolean getListening() {
		return this.listening;
	}
	
	public void setToolFlag(boolean flag, Integer index) {
		this.toolFlag = flag;
		this.toolIndex = index;
	}
	
	public boolean getToolFlag() {
		return this.toolFlag;
	}
	
	public Integer getToolIndex() {
		return this.toolIndex;
	}
	
	public String getPlayer() {
		return this.name;
	}
	
	public UUID getPlayerID() {
		return this.playerID;
	}
	
	public PreferenceList getPreferences() {
		return this.preferences;
	}
}