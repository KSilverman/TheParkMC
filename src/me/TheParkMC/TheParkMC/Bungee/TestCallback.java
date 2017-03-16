package me.TheParkMC.TheParkMC.Bungee;

import me.TheParkMC.TheParkMC.TheParkMCProxy;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;

public class TestCallback implements Callback<ServerPing>
{

	private TheParkMCProxy plugin;
	private String name;

	public TestCallback(TheParkMCProxy plugin, String name)
	{
		this.plugin = plugin;
		this.name = name;
	}

	@Override
	public void done(ServerPing ping, Throwable arg1)
	{
		plugin.setServerStatus(name, (ping.getFaviconObject() == null));
	}

}
