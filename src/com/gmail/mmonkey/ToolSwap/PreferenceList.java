package com.gmail.mmonkey.ToolSwap;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class PreferenceList implements Serializable {
	
	private static final long serialVersionUID = -5935859248616330267L;
	ArrayList<Preference> preferences = new ArrayList<Preference>();
	
	public PreferenceList() {}
	public PreferenceList(ArrayList<Preference> preferences) {
		this.preferences = preferences;
	}

	public ArrayList<Preference> getPreferences() {
		return this.preferences;
	}
	
	public void add(Material block, Tool tool) {
		if(contains(block)) {
			this.preferences.get(get(block)).setTool(tool);
		} else {
			this.preferences.add(new Preference(block, tool));
		}
	}
	
	public void remove(Material block) {
		if(contains(block)) {
			this.preferences.remove(get(block));
		}
	}
	
	public void remove(Integer index) {
		if(this.preferences.size() >= index && !this.preferences.isEmpty()) {
			this.preferences.remove((index - 1));
		}
	}
	
	public boolean contains(Material block) {
		for(int i = 0; i < this.preferences.size(); i++) {
			if(this.preferences.get(i).getBlock().equals(block)) {
				return true;
			}
		}
		return false;
	}
	
	public int get(Material block) {
		int result = -1;
		for(int i = 0; i < this.preferences.size(); i++) {
			if(this.preferences.get(i).getBlock().equals(block)) {
				result = i;
			}
		}
		return result;
	}
	
	public Tool getTool(Material block) {
		return this.preferences.get(get(block)).getTool();
	}
	
	public ArrayList<String> print() {
		ArrayList<String> s = new ArrayList<String>();
		for(int i = 0; i < preferences.size(); i++) {
			s.add(ChatColor.GOLD + preferences.get(i).getBlock().toString() + ChatColor.WHITE + " - " + preferences.get(i).getTool().toString());
		}
		return s;
	}

}