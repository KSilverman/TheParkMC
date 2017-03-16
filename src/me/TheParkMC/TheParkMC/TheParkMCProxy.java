package me.TheParkMC.TheParkMC;

import java.util.HashMap;
import java.util.Map;
import net.md_5.bungee.api.plugin.Plugin;

public class TheParkMCProxy extends Plugin {

	public Map<String, Boolean> serverStatus = new HashMap<String, Boolean>();
	
	private static TheParkMCProxy instance;

	@Override
	public void onEnable()
	{
		instance = this;
	}
	
	public final Map<String, Boolean> getAllServerStatuses()
	{
		return serverStatus;
	}

	public final boolean getServerStatus(String name)
	{
		return serverStatus.get(name);
	}

	public final void setServerStatus(String name, boolean online)
	{
		serverStatus.put(name, online);
	}
	
	public static TheParkMCProxy getInstance()
	{
		return instance;
	}

}

