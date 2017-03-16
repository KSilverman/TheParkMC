package me.TheParkMC.TheParkMC;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.TheParkMC.TheParkMC.Commands.CMD_Override;
import me.TheParkMC.TheParkMC.Commands.SimpleCommand;
import me.TheParkMC.TheParkMC.Events.EVT_Block;
import me.TheParkMC.TheParkMC.Events.EVT_Fly;
import me.TheParkMC.TheParkMC.Events.EVT_Interact;
import me.TheParkMC.TheParkMC.Events.EVT_InvClick;
import me.TheParkMC.TheParkMC.Events.EVT_Join;
import me.TheParkMC.TheParkMC.Events.EVT_Move;
import me.TheParkMC.TheParkMC.Events.EVT_Plugin;
import me.TheParkMC.TheParkMC.Events.EVT_Quit;
import me.TheParkMC.TheParkMC.Events.EVT_Tag;
import me.TheParkMC.TheParkMC.Objects.BarManager;
import me.TheParkMC.TheParkMC.Objects.BungeeManager;
import me.TheParkMC.TheParkMC.Objects.ItemManager;
import me.TheParkMC.TheParkMC.Objects.MySQLManager;
import me.TheParkMC.TheParkMC.Objects.Party;
import me.TheParkMC.TheParkMC.Objects.PartyManager;
import me.TheParkMC.TheParkMC.Objects.PlayerPet;
import me.TheParkMC.TheParkMC.Objects.PlayerPetManager;
import me.TheParkMC.TheParkMC.Objects.RankManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.theparkmc.API.scoreboard.ScoreboardScroller;

public class TheParkMC extends JavaPlugin {

	private static TheParkMC instance;

	RankManager rm;
	MySQLManager msqlm;
	ItemManager im;
	BungeeManager bm;
	PartyManager pm;
	PlayerPetManager pmm;

	ScoreboardScroller ss;

	List<Party> parties;
	
	List<String> overrided;
	List<String> jumped;
	List<String> propllerhats;

	Map<String, Integer> cooldownplayers;
	Map<String, PlayerPet> petplayers;
	
	File rootDir;
	File playersDir;
	boolean firstTime;

	@Override
	public void onEnable()
	{
		instance = this;
		rm = new RankManager();
		msqlm = new MySQLManager();
		im = new ItemManager();
		bm = new BungeeManager();
		pm = new PartyManager();
		pmm = new PlayerPetManager();
		ss = new ScoreboardScroller();
		parties = new ArrayList<Party>();
		overrided = new ArrayList<String>();
		jumped = new ArrayList<String>();
		propllerhats = new ArrayList<String>();
		cooldownplayers = new HashMap<String, Integer>();
		petplayers = new HashMap<String, PlayerPet>();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, ss, 0L, 5L);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BarManager(), 0L, 5L);
		rm.clearAllLists();
		bm.clearAllLists();
		init_commands();
		init_events();
		init_bungee();
		init_files();
		init_playerdata();
		System.out.println(getStarter() + "ENABLED!");
	}

	@Override
	public void onDisable()
	{
		try
		{
			if(msqlm.getConnection() != null && !msqlm.getConnection().isClosed())
				msqlm.getConnection().close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		rm.clearAllLists();
		bm.clearAllLists();
		System.out.println(getStarter() + "DISABLED!");
	}

	private void init_events()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EVT_Join(), this);
		pm.registerEvents(new EVT_Quit(), this);
		pm.registerEvents(new EVT_Interact(), this);
		pm.registerEvents(new EVT_InvClick(), this);
		pm.registerEvents(new EVT_Block(), this);
		pm.registerEvents(new EVT_Tag(), this);
		pm.registerEvents(new EVT_Move(), this);
		pm.registerEvents(new EVT_Fly(), this);
	}

	private void init_commands()
	{
		SimpleCommand.registerCommands(this, new CMD_Override());
	}

	private void init_bungee()
	{
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new EVT_Plugin());
	}
	
	private void init_files()
	{
		rootDir = new File(getDataFolder() + "");
        if (!rootDir.exists())
        {
            rootDir.mkdir();
            firstTime = true;
        }
		
		playersDir = new File(getDataFolder() + "/players/");
        if (!playersDir.exists())
            playersDir.mkdir();
	}

	@SuppressWarnings("deprecation")
	private void init_playerdata()
	{
		File playerFilesDir = new File("world" + "/playerdata");
		if(playerFilesDir.isDirectory())
		{
			String[] playerDats = playerFilesDir.list();
			for (int i = 0; i < playerDats.length; i++) 
			{
				File datFile = new File(playerFilesDir, playerDats[i]); 
				datFile.delete();
				System.out.println("[TheParkMC] Deleted player data file for " + getServer().getOfflinePlayer(playerDats[i]) + ".dat!");
			}
		}
		File[] playerFiles = playersDir.listFiles();
        for (int i = 0; i < playerFiles.length; i++)
        {
            File f = playerFiles[i];
            try
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                PlayerPet t = (PlayerPet) ois.readObject();
                petplayers.put(t.getName(), t);
                ois.close();
                System.out.println("Loaded PET player data for " + t.getName());
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
	}

	public static TheParkMC getInstance()
	{
		return instance;
	}

	public String getStarter()
	{
		return ChatColor.AQUA + "[TheParkMC] ";
	}

	public String getPermMsg()
	{
		return ChatColor.DARK_RED + "You do not have permission to execute this command!";
	}

	public RankManager getRankManager()
	{
		return rm;
	}

	public MySQLManager getMySQLManager()
	{
		return msqlm;
	}

	public ItemManager getItemManager()
	{
		return im;
	}

	public BungeeManager getBungeeManager()
	{
		return bm;
	}
	
	public PartyManager getPartyManager()
	{
		return pm;
	}
	
	public PlayerPetManager getPlayerPetManager()
	{
		return pmm;
	}

	public ScoreboardScroller getScorebobardScroller()
	{
		return ss;
	}
	
	public List<Party> getParties()
	{
		return parties;
	}

	public List<String> getOverridedPlayers()
	{
		return overrided;
	}

	public List<String> getJumpedPlayers()
	{
		return jumped;
	}
	
	public List<String> getPropellerHatPlayers()
	{
		return propllerhats;
	}

	public Map<String, Integer> getCooldownPlayers()
	{
		return cooldownplayers;
	}
	
	public Map<String, PlayerPet> getPlayerPets()
	{
		return petplayers;
	}
}
