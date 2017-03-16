package me.TheParkMC.TheParkMC.Objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RankManager {

	List<String> owners;
	List<String> admins;
	List<String> mods;
	List<String> coders;
	List<String> builders;
	List<String> vips;
	List<String> gods;
	List<String> legends;
	List<String> masters;

	public RankManager()
	{
		owners = new ArrayList<String>();
		admins = new ArrayList<String>();
		mods = new ArrayList<String>();
		coders = new ArrayList<String>();
		builders = new ArrayList<String>();
		vips = new ArrayList<String>();
		gods = new ArrayList<String>();
		legends = new ArrayList<String>();
		masters = new ArrayList<String>();
	}

	public List<String> getOwners()
	{
		return owners;
	}

	public List<String> getAdmins()
	{
		return admins;
	}

	public List<String> getMods()
	{
		return mods;
	}

	public List<String> getBuilders()
	{
		return builders;
	}

	public List<String> getCoders()
	{
		return coders;
	}

	public List<String> getVips()
	{
		return vips;
	}

	public List<String> getGods()
	{
		return gods;
	}

	public List<String> getLegends()
	{
		return legends;
	}

	public List<String> getMasters()
	{
		return masters;
	}

	public boolean modUp(Player p)
	{
		if(getOwners().contains(p.getName()) || getAdmins().contains(p.getName()) || getCoders().contains(p.getName()) || getMods().contains(p.getName()))
			return true;
		else
			return false;
	}

	public boolean vipUp(Player p)
	{
		if(getOwners().contains(p.getName()) || getAdmins().contains(p.getName()) || getCoders().contains(p.getName()) || getMods().contains(p.getName()) || getVips().contains(p.getName()) || getBuilders().contains(p.getName()))
			return true;
		else
			return false;
	}

	public boolean coderUp(Player p)
	{
		if(getOwners().contains(p.getName()) || getAdmins().contains(p.getName()) || getCoders().contains(p.getName()))
			return true;
		else
			return false;
	}

	public boolean godUp(Player p)
	{
		if(getOwners().contains(p.getName()) || getAdmins().contains(p.getName()) || getCoders().contains(p.getName()) || getMods().contains(p.getName()) || getVips().contains(p.getName()) || getBuilders().contains(p.getName()) || getGods().contains(p.getName()))
			return true;
		else
			return false;
	}

	public boolean legendUp(Player p)
	{
		if(getOwners().contains(p.getName()) || getAdmins().contains(p.getName()) || getCoders().contains(p.getName()) || getMods().contains(p.getName()) || getVips().contains(p.getName()) || getBuilders().contains(p.getName()) || getGods().contains(p.getName()) || getLegends().contains(p.getName()))
			return true;
		else
			return false;
	}

	public boolean isRanked(Player p)
	{
		if(getOwners().contains(p.getName()) || getAdmins().contains(p.getName()) || getCoders().contains(p.getName()) || getMods().contains(p.getName()) || getVips().contains(p.getName()) || getBuilders().contains(p.getName()) || getGods().contains(p.getName()) || getLegends().contains(p.getName()) || getMasters().contains(p.getName()))
			return true;
		else
			return false;
	}

	public void clearAllLists()
	{
		getOwners().clear();
		getAdmins().clear();
		getCoders().clear();
		getMods().clear();
		getVips().clear();
		getBuilders().clear();
		getGods().clear();
		getLegends().clear();
		getMasters().clear();
	}

	public ChatColor getRankColor(Player player)
	{
		if(getOwners().contains(player.getName()))
			return ChatColor.DARK_RED;
		else if(getAdmins().contains(player.getName()))
			return ChatColor.RED;
		else if(getMods().contains(player.getName()))
			return ChatColor.DARK_AQUA;
		else if(getCoders().contains(player.getName()))
			return ChatColor.RED;
		else if(getBuilders().contains(player.getName()))
			return ChatColor.LIGHT_PURPLE;
		else if(getVips().contains(player.getName()))
			return ChatColor.DARK_PURPLE;
		else if(getGods().contains(player.getName()))
			return ChatColor.AQUA;
		else if(getLegends().contains(player.getName()))
			return ChatColor.GOLD;
		else if(getMasters().contains(player.getName()))
			return ChatColor.GRAY;
		return ChatColor.GREEN;
	}

	public String getRankName(String player)
	{
		if(getMasters().contains(player))
			return ChatColor.DARK_GRAY + "MASTER";
		if(getLegends().contains(player))
			return ChatColor.GOLD + "LEGEND";
		if(getGods().contains(player))
			return ChatColor.AQUA + "GOD";
		if(getOwners().contains(player))
			return ChatColor.DARK_RED + "OWNER";
		if(getAdmins().contains(player))
			return ChatColor.RED + "ADMIN";
		if(getMods().contains(player))
			return ChatColor.DARK_AQUA + "MOD";
		if(getVips().contains(player))
			return ChatColor.DARK_PURPLE + "VIP";
		if(getBuilders().contains(player))
			return ChatColor.LIGHT_PURPLE + "MAP MAKER";
		if(getCoders().contains(player))
			return ChatColor.RED + "CODER";
		return ChatColor.GREEN + "NONE";
	}
	
	public String getHighestOnlineStaffMember()
	{
		String highest = "NONE";
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(modUp(p))
			{
				if(getOwners().contains(p.getName()))
				{
					highest = p.getName();
					return highest;
				}
				else if(getAdmins().contains(p.getName()))
				{
					highest = p.getName();
					return highest;
				}
				else if(getCoders().contains(p.getName()))
				{
					highest = p.getName();
					return highest;
				}
				else if(getMods().contains(p.getName()))
				{
					highest = p.getName();
					return highest;
				}
			}
		}
		return highest;
	}

}
