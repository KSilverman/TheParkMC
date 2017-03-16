package me.TheParkMC.TheParkMC.Objects;

import java.util.Random;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PartyManager {
	
	public PartyManager()
	{
		
	}
	
	public Party getParty(Player p)
	{
		if(isInParty(p))
		{
			for(Party pt : TheParkMC.getInstance().getParties())
			{
				if(pt.getPlayers().contains(p.getName()))
					return pt;
			}
		}
		return null;
	}
	
	public void removeFromParty(Player p)
	{
		if(isInParty(p))
		{
			for(Party pt : TheParkMC.getInstance().getParties())
			{
				if(pt.getPlayers().contains(p.getName()))
				{
					if(isPartyLeader(p))
					{
						if(pt.getPlayers().size() != 1)
						{
							pt.getPlayers().remove(p.getName());
							Player newleader = pt.getRealPlayers()[new Random().nextInt(pt.getPlayers().size()) + 1];
							pt.setPartyLeader(newleader);
							newleader.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GOLD + "You are now the party leader to party " + ChatColor.LIGHT_PURPLE + pt.getName());
							p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.BLUE + "You have left party " + ChatColor.LIGHT_PURPLE + pt.getName());
						}
						else
						{
							p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "You have left and destroyed party " + ChatColor.LIGHT_PURPLE + pt.getName());
							TheParkMC.getInstance().getParties().remove(pt);
						}
					}
					else
					{
						p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.BLUE + "You have left party " + ChatColor.LIGHT_PURPLE + pt.getName());
						pt.getPlayers().remove(p.getName());
						sendPartyMsg(pt, TheParkMC.getInstance().getStarter() + ChatColor.BLUE + p.getName() + ChatColor.RED + " has left your party!");
					}
					System.out.println("[PARTY MANAGER] Removed player " + p.getName() + " from party " + pt.getName() + "!");
				}
			}
		}
	}
	
	public void addToParty(Player p, Party pt)
	{
		if(isInParty(p))
		{
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.DARK_RED + "Error putting you into party " + ChatColor.RED + pt.getName() + ChatColor.DARK_RED + " because you are already in party " + ChatColor.LIGHT_PURPLE + getParty(p).getName());
			return;
		}
		pt.getPlayers().add(p.getName());
		p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.YELLOW + "Successfully added to party " + ChatColor.LIGHT_PURPLE + pt.getName());
		System.out.println("[PARTY MANAGER] Added player " + p.getName() + " to party " + pt.getName() + "!");
	}
	
	public boolean isInParty(Player p)
	{
		for(Party pt : TheParkMC.getInstance().getParties())
		{
			if(pt.getPlayers().contains(p.getName()))
				return true;
		}
		return false;
	}
	
	public boolean isPartyLeader(Player p)
	{
		if(isInParty(p))
		{
			for(Party pt : TheParkMC.getInstance().getParties())
			{
				if(pt.getPlayers().contains(p.getName()))
				{
					if(pt.getPartyLeader().equals(p))
						return true;
				}
			}
		}
		return false;
	}
	
	public void sendPartyMsg(Party pt, String msg)
	{
		for(Player p : pt.getRealPlayers())
			p.sendMessage(msg);
	}

}
