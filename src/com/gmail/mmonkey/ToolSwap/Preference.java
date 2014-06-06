package com.gmail.mmonkey.ToolSwap;

import java.io.Serializable;

import org.bukkit.Material;

public class Preference implements Serializable {
	
	private static final long serialVersionUID = 3326603629099416490L;
	
	Material block;
	Tool tool;
	
	public Preference(Material block, Tool tool) {
		this.block = block;
		this.tool = tool;
	}
	
	public Material getBlock() {
		return this.block;
	}
	
	public Tool getTool() {
		return this.tool;
	}
	
	public void setBlock(Material block) {
		this.block = block;
	}
	
	public void setTool(Tool tool) {
		this.tool = tool;
	}

}
