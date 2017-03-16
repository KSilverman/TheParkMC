package me.TheParkMC.TheParkMC.Objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemManager {

	public ItemManager()
	{

	}

	public ItemStack setName(ItemStack is, String name, ChatColor colour)
	{
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(colour + name);
		is.setItemMeta(im);
		return is;
	}
	
	public ItemStack setLore(ItemStack is, String description)
	{
		List<String> temp = new ArrayList<String>();
		ItemMeta im = is.getItemMeta();
		temp.add(description);
		im.setLore(temp);
		is.setItemMeta(im);
		return is;
	}
	
	public ItemStack setNameAndLore(ItemStack is, String name, String description)
	{
		List<String> temp = new ArrayList<String>();
		ItemMeta im = is.getItemMeta();
		temp.add(description);
		im.setDisplayName(name);
		im.setLore(temp);
		is.setItemMeta(im);
		return is;
	}

	public ItemStack setColorForLeather(ItemStack is, String Name, Color color, ChatColor colorr)
	{
		LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
		im.setColor(color);
		im.setDisplayName(colorr + Name);
		is.setItemMeta(im);
		return is;
	}
}
