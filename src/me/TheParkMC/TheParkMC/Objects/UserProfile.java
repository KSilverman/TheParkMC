package me.TheParkMC.TheParkMC.Objects;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {
	
	String name;
	List<String> pendingrequests;
	List<String> blocked;
	List<String> friends;
	
	public UserProfile(String name)
	{
		this.name = name;
		pendingrequests = new ArrayList<String>();
		blocked = new ArrayList<String>();
		friends = new ArrayList<String>();
	}
	
	public List<String> getBlockedPlayers()
	{
		return blocked;
	}
	
	public List<String> getFriends()
	{
		return friends;
	}
	
	public String getName()
	{
		return name;
	}
	
	public List<String> getPendingRequests() 
	{
		return pendingrequests;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
}
