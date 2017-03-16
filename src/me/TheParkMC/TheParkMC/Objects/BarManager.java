package me.TheParkMC.TheParkMC.Objects;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BarManager extends BukkitRunnable
{
	public int msg = 0;
	public int runs = 0;
	public int color = 0;

	public void run()
	{
		float f = this.runs / 40 * 100 + 1;
		for (Player p : Bukkit.getOnlinePlayers())
			BarAPI.setMessage(p, getColor(this.color) + getText(this.msg), f);
		this.runs += 1;
		this.color += 1;
		if (this.color > 5)
			this.color = 0;
		if (this.runs >= 40)
		{
			this.msg += 1;
			this.runs = 0;
			if (this.msg > 4)
				this.msg = 0;
		}
	}

	public String getText(int msgNumb)
	{
		switch (msgNumb)
		{
		case 0:
			return ChatColor.GOLD + "" + ChatColor.BOLD + "Welcome to " + ChatColor.GREEN + ChatColor.BOLD + "TheParkMC!";
		case 1:
			return ChatColor.GREEN + "Purchase ranks at " + ChatColor.GOLD + ChatColor.BOLD + "theparkmc.com/shop";
		case 2:
			return ChatColor.AQUA + "Come check out the new custom " + ChatColor.RED + ChatColor.BOLD + "SurvivalGames!";
		case 3:
			return ChatColor.GOLD + "" +  ChatColor.BOLD + "Right-click a sign to join a game!";
		case 4:
			return ChatColor.GREEN + "" +  ChatColor.BOLD + "Use the compass to teleport to the different gamemodes!";
		}
		return ChatColor.GOLD + "" +  ChatColor.BOLD + "Welcome to " + ChatColor.GREEN + ChatColor.BOLD + "TheParkMC!";
	}

	public String getColor(int color)
	{
		switch (color) 
		{
		case 0:
			return ChatColor.LIGHT_PURPLE + "" +  ChatColor.BOLD + "THEPARKMC " + ChatColor.RESET + "- ";
		case 1:
			return ChatColor.DARK_GREEN + "" +  ChatColor.BOLD + "THEPARKMC " + ChatColor.RESET + "- ";
		case 2:
			return ChatColor.GREEN + "" +  ChatColor.BOLD + "THEPARKMC " + ChatColor.RESET + "- ";
		case 3:
			return ChatColor.DARK_AQUA + "" +  ChatColor.BOLD + "THEPARKMC " + ChatColor.RESET + "- ";
		case 4:
			return ChatColor.AQUA + "" +  ChatColor.BOLD + "THEPARKMC " + ChatColor.RESET + "- ";
		case 5:
			return ChatColor.DARK_PURPLE + "" +  ChatColor.BOLD + "THEPARKMC " + ChatColor.RESET + "- ";
		}
		return ChatColor.RED + "" +  ChatColor.BOLD + "THEPARKMC " + ChatColor.RESET + "- ";
	}
}
