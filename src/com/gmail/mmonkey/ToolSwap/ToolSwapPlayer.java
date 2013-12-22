package com.gmail.mmonkey.ToolSwap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class ToolSwapPlayer {
	
	String player;
	boolean swap;
	boolean listening = false;
	boolean toolFlag = false;
	int toolIndex;
	ArrayList<HashMap<Material, HashMap<Material, HashMap<String, Integer>>>> preferenceList = new ArrayList<HashMap<Material, HashMap<Material, HashMap<String, Integer>>>>();
	
	public ToolSwapPlayer(String player, boolean enabled) {
		this.player = player;
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
		return this.player;
	}
	public void addToPreferenceList(Material block, Material toolMaterial, Map<Enchantment, Integer> enchantments) {
		HashMap<String, Integer> enchants = new HashMap<String, Integer>();
		for(Map.Entry<Enchantment, Integer> entry: enchantments.entrySet()) {
			enchants.put(entry.getKey().getName(), entry.getValue());
		}
		HashMap<Material, HashMap<String, Integer>> tool = new HashMap<Material, HashMap<String, Integer>>();
		tool.put(toolMaterial, enchants);
		HashMap<Material, HashMap<Material, HashMap<String, Integer>>> pref = new HashMap<Material, HashMap<Material, HashMap<String, Integer>>>();
		pref.put(block, tool);
		preferenceList.add(pref);
	}
	public void addToPreferenceList(Material block, Material toolMaterial) {
		HashMap<String, Integer> enchants = new HashMap<String, Integer>();
		enchants.put("NOT_ENCHANTED", 0);
		HashMap<Material, HashMap<String, Integer>> tool = new HashMap<Material, HashMap<String, Integer>>();
		tool.put(toolMaterial, enchants);
		HashMap<Material, HashMap<Material, HashMap<String, Integer>>> pref = new HashMap<Material, HashMap<Material, HashMap<String, Integer>>>();
		pref.put(block, tool);
		preferenceList.add(pref);
	}
	public HashMap<Material, HashMap<Material, HashMap<String, Integer>>> getPreferences() {
		HashMap<Material, HashMap<Material, HashMap<String, Integer>>> result = new HashMap<Material, HashMap<Material, HashMap<String, Integer>>>();
		for(int i = 0; i < preferenceList.size(); i++) {
			for(Map.Entry<Material, HashMap<Material, HashMap<String, Integer>>> pref: preferenceList.get(i).entrySet()) {
				result.put(pref.getKey(), pref.getValue());
			}
		}
		return result;
	}
	public ArrayList<String> preferenceListToString() {
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < preferenceList.size(); i++ ) {
			for(Map.Entry<Material, HashMap<Material, HashMap<String, Integer>>> blocks: preferenceList.get(i).entrySet()) {
				for(Map.Entry<Material, HashMap<String, Integer>> tools: blocks.getValue().entrySet() ) {
					
					ArrayList<String> enchantArray = new ArrayList<String>();
					StringBuilder enchantString = new StringBuilder();
					
					for(Map.Entry<String, Integer> enchants: tools.getValue().entrySet()) {
						
						if(enchants.getKey().equals("NOT_ENCHANTED")) {
							enchantArray.add(" ");
						} else {
							enchantArray.add(convertEnchantmentName(enchants.getKey()) + ": " + convertNumToRoman(enchants.getValue()));
						}
					}
					
					for(int j = 0; j < enchantArray.size(); j++) {
						enchantString.append(enchantArray.get(j));
						if(j != (enchantArray.size() - 1)) {
							enchantString.append(", ");
						}
					}
					
					result.add(Integer.toString((i + 1)) + " - " + ChatColor.GOLD + blocks.getKey() + ChatColor.WHITE + " - " + ChatColor.BLUE + tools.getKey() + ChatColor.WHITE + " - " + ChatColor.GREEN + enchantString.toString());
				}
			}
		}
		return result;
	}
	public void removeListItem(Integer index) {
		if(preferenceList.size() >= index && !preferenceList.isEmpty()) {
			preferenceList.remove((index - 1));
		}
	}
	
	public Material getPreferredItem(Material block) {
		Material result = null;
		HashMap<Material, HashMap<String, Integer>> tool = new HashMap<Material, HashMap<String, Integer>>();
		tool = this.getPreferences().get(block);
		for(Map.Entry<Material, HashMap<String, Integer>> pref: tool.entrySet()) {
			result = pref.getKey();
		}
		return result;
	}
	public Map<Enchantment, Integer> getEnchantments(Material block, Material tool) {
		Map<Enchantment, Integer> result = new HashMap<Enchantment, Integer>();
		HashMap<String, Integer> enchants = this.getPreferences().get(block).get(tool);
		for(Map.Entry<String, Integer> convert: enchants.entrySet()) {
			if(convert.getKey().equals("NOT_ENCHANTED")) {
				return null;
			} else {
				result.put(Enchantment.getByName(convert.getKey()), convert.getValue());
			}
		}
		return result;
	}
	public String convertEnchantmentName(String enchantment) {
    	String result = null;
    	if(enchantment.equalsIgnoreCase("ARROW_DAMAGE")) {
    		result = "Power";
    	} else if(enchantment.equalsIgnoreCase("ARROW_FIRE")) {
    		result = "Flame";
    	} else if(enchantment.equalsIgnoreCase("ARROW_INFINITE")) {
    		result = "Infinity";
    	} else if(enchantment.equalsIgnoreCase("ARROW_KNOCKBACK")) {
    		result = "Punch";
    	} else if(enchantment.equalsIgnoreCase("DAMAGE_ALL")) {
    		result = "Sharpness";
    	} else if(enchantment.equalsIgnoreCase("DAMAGE_ARTHROPODS")) {
    		result = "Bane of Arthropods";
    	} else if(enchantment.equalsIgnoreCase("DAMAGE_UNDEAD")) {
    		result = "Smite";
    	} else if(enchantment.equalsIgnoreCase("DIG_SPEED")) {
    		result = "Efficiency";
    	} else if(enchantment.equalsIgnoreCase("DURABILITY")) {
    		result = "Unbreaking";
    	} else if(enchantment.equalsIgnoreCase("FIRE_ASPECT")) {
    		result = "Fire Aspect";
    	} else if(enchantment.equalsIgnoreCase("KNOCKBACK")) {
    		result = "Knockback";
    	} else if(enchantment.equalsIgnoreCase("LOOT_BONUS_BLOCKS")) {
    		result = "Fortune";
    	} else if(enchantment.equalsIgnoreCase("LOOT_BONUS_MOBS")) {
    		result = "Looting";
    	} else if(enchantment.equalsIgnoreCase("LUCK")) {
    		result = "Luck of the Sea";
    	} else if(enchantment.equalsIgnoreCase("LURE")) {
    		result = "Lure";
    	} else if(enchantment.equalsIgnoreCase("OXYGEN")) {
    		result = "Respiration";
    	} else if(enchantment.equalsIgnoreCase("PROTECTION_ENVIRONMENTAL")) {
    		result = "Protection";
    	} else if(enchantment.equalsIgnoreCase("PROTECTION_EXPLOSIONS")) {
    		result = "Blast Protection";
    	} else if(enchantment.equalsIgnoreCase("PROTECTION_FALL")) {
    		result = "Feather Falling";
    	} else if(enchantment.equalsIgnoreCase("PROTECTION_FIRE")) {
    		result = "Fire Protection";
    	} else if(enchantment.equalsIgnoreCase("PROTECTION_PROJECTILE")) {
    		result = "Projectile Protection";
    	} else if(enchantment.equalsIgnoreCase("SILK_TOUCH")) {
    		result = "Silk Touch";
    	} else if(enchantment.equalsIgnoreCase("THORNS")) {
    		result = "Thorns";
    	} else if(enchantment.equalsIgnoreCase("WATER_WORKER")) {
    		result = "Aqua Affinity";
    	} else {
    		result = enchantment;
    	}
    	return result;
    }
	public String convertNumToRoman(Integer num) {
		String result = null;
		if(num == 1) {
			result = "I";
		} else if(num == 2) {
			result = "II";
		} else if(num == 3) {
			result = "III";
		} else if(num == 4) {
			result = "IV";
		} else if(num == 5) {
			result = "V";
		} else if(num == 6) {
			result = "VI";
		} else if(num == 7) {
			result = "VII";
		} else if(num == 8) {
			result = "VIII";
		} else if(num == 9) {
			result = "IX";
		} else if(num == 10) {
			result = "X";
		} else {
			result = num.toString();
		}
		return result;
	}
}