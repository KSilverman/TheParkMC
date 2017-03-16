package me.TheParkMC.TheParkMC.Objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Party {
	
	String name;
	Player partyLeader;
	List<String> players;
	
	public Party(String name, Player partyLeader)
	{
		this.name = name;
		this.partyLeader = partyLeader;
		players = new ArrayList<String>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public Player getPartyLeader()
	{
		return partyLeader;
	}
	
	public List<String> getPlayers()
	{
		return players;
	}
	
	@SuppressWarnings("deprecation")
	public Player[] getRealPlayers()
	{
		Player[] temp = new Player[players.size()];
		for(int i = 0; i < players.size(); i++)
			temp[i] = Bukkit.getPlayer(players.get(i));
		return temp;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setPartyLeader(Player partyLeader)
	{
		this.partyLeader = partyLeader;
	}

}
