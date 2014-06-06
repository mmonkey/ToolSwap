package com.gmail.mmonkey.ToolSwap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.Serializable;
import java.lang.StringBuilder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class Tool implements Serializable {
	
	private static final long serialVersionUID = -1263162711672517689L;
	
	Material material;
	boolean isEnchanted = false;
	Map<SerializedEnchantment, Integer> enchantments;
	
	public Tool(Material material, Map<Enchantment, Integer> enchantments) {
		this.material = material;
		this.isEnchanted = true;
		this.enchantments = convertToSerializedEnchantments(enchantments);
	}
	
	public Tool(Material material) {
		this.material = material;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public Map<Enchantment, Integer> getEnchantments() {
		return convertToEnchantments(this.enchantments);
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public void setEnchantments(Map<Enchantment, Integer> enchantments) {
		this.enchantments = convertToSerializedEnchantments(enchantments);
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		int i = 0;
		
		if(this.isEnchanted) {
			
			for(Entry<SerializedEnchantment, Integer> e: this.enchantments.entrySet()) {
				if(i == 0) {
					s.append(ChatColor.GREEN + convertEnchantmentName(e.getKey()) + " " + convertNumToRoman(e.getValue()));
				} else {
					s.append(ChatColor.GREEN + ", " + convertEnchantmentName(e.getKey()) + " " + convertNumToRoman(e.getValue()));
				}
				i++;
			}
			
		} else {
			s.append(ChatColor.GREEN + "NOT_ENCHANTED");
		}
		return ChatColor.BLUE + material.toString() + ChatColor.WHITE + " - " + s.toString();
	}
	
	public String convertEnchantmentName(SerializedEnchantment e) {
    	String result = null;
    	String enchantment = e.getName();
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
		switch(num) {
			case 1: result = "I";
				break;
			case 2: result = "II";
				break;
			case 3: result = "III";
				break;
			case 4: result = "IV";
				break;
			case 5: result = "V";
				break;
			case 6: result = "VI";
				break;
			case 7: result = "VII";
				break;
			case 8: result = "VIII";
				break;
			case 9: result = "IX";
				break;
			case 10: result = "X";
				break;
		}
		if(result == null) {
			result = num.toString();
		}
		return result;
	}
	
	public Map<SerializedEnchantment, Integer> convertToSerializedEnchantments(Map<Enchantment, Integer> enchantments) {
		Map<SerializedEnchantment, Integer> result = new HashMap<SerializedEnchantment, Integer>();
		for(Entry<Enchantment, Integer> e: enchantments.entrySet()) {
			result.put(new SerializedEnchantment(e.getKey().getName()), e.getValue());
		}
		return result;
	}
	
	public Map<Enchantment, Integer> convertToEnchantments(Map<SerializedEnchantment, Integer> enchantments) {
		Map<Enchantment, Integer> result = new HashMap<Enchantment, Integer>();
		for(Entry<SerializedEnchantment, Integer> e: enchantments.entrySet()) {
			result.put(Enchantment.getByName(e.getKey().getName()), e.getValue());
		}
		return result;
	}
}
