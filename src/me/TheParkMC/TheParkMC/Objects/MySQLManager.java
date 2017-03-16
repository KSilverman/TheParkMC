package me.TheParkMC.TheParkMC.Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.entity.Player;

public class MySQLManager {
private Connection connection;
	
	public MySQLManager()
	{
		
	}
	
	private synchronized void openConnection()
	{
		try
		{
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "username", "password");
    	}
		catch(Exception e)
		{
    	}
	}
	
	private synchronized void closeConnection()
	{
    	try
    	{
    		connection.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
	
    private synchronized boolean playerRankContainsPlayer(Player player)
    {
    	try
    	{
    		PreparedStatement sql = connection.prepareStatement("SELECT * FROM `player_rank` WHERE player=?;");
    		sql.setString(1, player.getUniqueId().toString());
    		ResultSet resultSet = sql.executeQuery();
    		boolean containsPlayer = resultSet.next();
    		
    		sql.close();
    		resultSet.close();
    		
    		return containsPlayer;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }
    
    private synchronized boolean playerFriendContainsPlayer(Player player)
    {
    	try
    	{
    		PreparedStatement sql = connection.prepareStatement("SELECT * FROM `Friends` WHERE player=?;");
    		sql.setString(1, player.getUniqueId().toString());
    		ResultSet resultSet = sql.executeQuery();
    		boolean containsPlayer = resultSet.next();
    		
    		sql.close();
    		resultSet.close();
    		
    		return containsPlayer;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }
    
    public void loadPlayerFriends(Player player)
    {
    	openConnection();
    	try
    	{
    		
    		if(playerFriendContainsPlayer(player))
    		{
    			for(int i = 1; i < 5; i++)
    			{
    				PreparedStatement sql = connection.prepareStatement("SELECT Freind" + String.valueOf(i) + " FROM `player_data` WHERE player=?;");
    				sql.setString(1, player.getUniqueId().toString());
    				ResultSet result = sql.executeQuery();
        			result.next();
        			//add the result set to that players friends list
    			}
    		}
    		else
    		{
    			PreparedStatement newPlayer = connection.prepareStatement("INSERT INTO `Friends` values(?,?,?,?,?,?);");
    			newPlayer.setString(1, player.getUniqueId().toString());
    			newPlayer.setString(2, "null");
    			newPlayer.setString(3, "null");
    			newPlayer.setString(4, "null");
    			newPlayer.setString(5, "null");
    			newPlayer.setString(6, "null");
    			newPlayer.execute();
    			newPlayer.close();
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		closeConnection();
    	}
    }
    
    public void givePlayerRank(Player player)
    {
    	openConnection();
    	try
    	{
    		String rank = "";
    		if(playerRankContainsPlayer(player))
    		{
    			PreparedStatement sql = connection.prepareStatement("SELECT rank FROM `player_rank` WHERE player=?;");
    			sql.setString(1, player.getUniqueId().toString());
    			
    			ResultSet result = sql.executeQuery();
    			result.next();
    			
    			rank = result.getString("rank");
    			
    			if(rank.equalsIgnoreCase("owner"))
    			{
        			TheParkMC.getInstance().getRankManager().getOwners().add(player.getName());
    			}
    			
    			if(rank.equalsIgnoreCase("vip"))
    			{
    				TheParkMC.getInstance().getRankManager().getVips().add(player.getName());
    			}
    			
    			if(rank.equalsIgnoreCase("mod"))
    			{
    				TheParkMC.getInstance().getRankManager().getMods().add(player.getName());
    			}
    			
    			if(rank.equalsIgnoreCase("master"))
    			{
    				TheParkMC.getInstance().getRankManager().getMasters().add(player.getName());
    			}
    			
    			if(rank.equalsIgnoreCase("admin"))
    			{
    				TheParkMC.getInstance().getRankManager().getAdmins().add(player.getName());
    			}
    			
    			if(rank.equalsIgnoreCase("legend"))
    			{
    				TheParkMC.getInstance().getRankManager().getLegends().add(player.getName());
    			}
    			if(rank.equalsIgnoreCase("builder"))
    			{
    				TheParkMC.getInstance().getRankManager().getBuilders().add(player.getName());
    			}
    			
    			if(rank.equalsIgnoreCase("coder"))
    			{
    				TheParkMC.getInstance().getRankManager().getCoders().add(player.getName());
    			}
    			
    			if(rank.equalsIgnoreCase("god"))
    			{
    				TheParkMC.getInstance().getRankManager().getGods().add(player.getName());
    			}
    			
    			sql.close();
    			result.close();
    		} 
    		else 
    		{
    			if(!(TheParkMC.getInstance().getRankManager().isRanked(player)))
    				return;
    			PreparedStatement newPlayer = connection.prepareStatement("INSERT INTO `player_rank` values(?,?);");
    			newPlayer.setString(1, player.getUniqueId().toString());
    			if(TheParkMC.getInstance().getRankManager().getOwners().contains(player.getName()))
    			{
    				newPlayer.setString(2, "owner");
    			} 
    			else if(TheParkMC.getInstance().getRankManager().getVips().contains(player.getName()))
    			{
    				newPlayer.setString(2, "vip");
    			} 
    			else if(TheParkMC.getInstance().getRankManager().getMods().contains(player.getName()))
    			{
    				newPlayer.setString(2, "mod");
    			} 
    			else if(TheParkMC.getInstance().getRankManager().getMasters().contains(player.getName()))
    			{
    				newPlayer.setString(2, "master");
    			} 
    			else if(TheParkMC.getInstance().getRankManager().getAdmins().contains(player.getName()))
    			{
    				newPlayer.setString(2, "admin");
    			} 
    			else if(TheParkMC.getInstance().getRankManager().getLegends().contains(player.getName()))
    			{
    				newPlayer.setString(2, "legend");
    			}  
    			else if(TheParkMC.getInstance().getRankManager().getBuilders().contains(player.getName()))
    			{
    				newPlayer.setString(2, "builder");
    			} 
    			else if(TheParkMC.getInstance().getRankManager().getCoders().contains(player.getName()))
    			{
    				newPlayer.setString(2, "coder");
    			} 
    			else if(TheParkMC.getInstance().getRankManager().getGods().contains(player.getName()))
    			{
    				newPlayer.setString(2, "god");
    			}
    			newPlayer.execute();
    			newPlayer.close();
    		}
    	} 
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	} 
    	finally 
    	{
    		closeConnection();
    	}
    }
	
	public Connection getConnection()
	{
		return connection;
	}

}
