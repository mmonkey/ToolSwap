package com.gmail.mmonkey.ToolSwap;

import java.io.Serializable;

import org.bukkit.enchantments.Enchantment;

public class SerializedEnchantment implements Serializable {

	private static final long serialVersionUID = 8678529849324090682L;
	String name;
	
	public SerializedEnchantment(String name) {
		this.name = name;
	}
	
	public SerializedEnchantment(Enchantment enchantment) {
		this.name = enchantment.getName();
	}
	
	public String getName() {
		return this.name;
	}

}
