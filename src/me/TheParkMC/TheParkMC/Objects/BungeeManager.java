package me.TheParkMC.TheParkMC.Objects;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeManager {
	
	List<String> allservernames;
	List<String> cwservernames;
	List<String> sgservernames;
	List<String> murderservernames;
	List<String> haservernames;
	List<String> kingsservernames;
	Map<String, Integer> serverplayeramounts;
	Map<String, Short> servernamesandips;
	
	public BungeeManager()
	{
		allservernames = new ArrayList<String>();
		cwservernames = new ArrayList<String>();
		sgservernames = new ArrayList<String>();
		murderservernames = new ArrayList<String>();
		haservernames = new ArrayList<String>();
		kingsservernames = new ArrayList<String>();
		serverplayeramounts = new HashMap<String, Integer>();
		servernamesandips = new HashMap<String, Short>();
	}
	
	public void sendToServer(String servername, Player p)
	{
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try
		{
			out.writeUTF("Connect");
			out.writeUTF(servername);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		p.sendPluginMessage(TheParkMC.getInstance(), "BungeeCord", b.toByteArray());
	}
	
	public void requestServerIP(String server, Player p)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ServerIP");
		out.writeUTF(server);
		p.sendPluginMessage(TheParkMC.getInstance(), "BungeeCord", out.toByteArray());
	}
	
	
	public Short getServerIP(String server, Player p)
	{
		requestServerIP(server, p);
		return getServerNamesAndIPs().get(server);
	}
	
	
	public void requestPlayerCount(String server, Player p)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("PlayerCount");
		out.writeUTF(server);
		p.sendPluginMessage(TheParkMC.getInstance(), "BungeeCord", out.toByteArray());
	}
	
	public int getPlayerCount(String server, Player p)
	{
		requestPlayerCount(server, p);
		return getServerPlayerAmounts().get(server);
	}
	
	public void requestServerNames(Player p)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        p.sendPluginMessage(TheParkMC.getInstance(), "BungeeCord", out.toByteArray());
	}
	
	public void sortServers(Player p)
	{
		for(String s : allservernames)
		{
			if(s.contains("cw"))
				cwservernames.add(s);
			else if(s.contains("sg"))
				sgservernames.add(s);
			else if(s.contains("m"))
				murderservernames.add(s);
			else if(s.contains("ha"))
				haservernames.add(s);
			else if(s.contains("k"))
				kingsservernames.add(s);
		}
	}
	
	public boolean isOnline(Short port)
    {
        try
        {
            Socket s = new Socket();
            s.connect(new InetSocketAddress("127.0.0.1", Integer.valueOf(port)));
            s.close();
            return true;
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
	
	public int getServerTotal(Player p)
	{
		return getServerNames().size();
	}
	
	public void clearAllLists()
	{
		allservernames.clear();
		cwservernames.clear();
		sgservernames.clear();
		murderservernames.clear();
		haservernames.clear();
		kingsservernames.clear();
		serverplayeramounts.clear();
		servernamesandips.clear();
	}
	
	public List<String> getServerNames()
	{
		return allservernames;
	}
	
	public List<String> getCWServerNames()
	{
		return cwservernames;
	}
	
	public List<String> getSGServerNames()
	{
		return sgservernames;
	}
	
	public List<String> getMurderServerNames()
	{
		return murderservernames;
	}
	
	public List<String> getHAServerNames()
	{
		return haservernames;
	}
	
	public List<String> getKingsServerNames()
	{
		return kingsservernames;
	}
	
	public Map<String, Integer> getServerPlayerAmounts()
	{
		return serverplayeramounts;
	}
	
	public Map<String, Short> getServerNamesAndIPs()
	{
		return servernamesandips;
	}
}
