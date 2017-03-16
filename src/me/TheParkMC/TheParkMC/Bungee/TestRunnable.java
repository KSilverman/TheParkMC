package me.TheParkMC.TheParkMC.Bungee;

import me.TheParkMC.TheParkMC.TheParkMC;
import me.TheParkMC.TheParkMC.TheParkMCProxy;
import net.md_5.bungee.api.config.ServerInfo;

public class TestRunnable implements Runnable
{

	private TheParkMCProxy plugin;

	public TestRunnable(TheParkMCProxy plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public void run() 
	{
		for(String s : TheParkMC.getInstance().getBungeeManager().getCWServerNames())
		{
			ServerInfo si = plugin.getProxy().getServerInfo(s);
			si.ping(new TestCallback(plugin, si.getName()));
		}
	}

}