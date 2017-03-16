package me.TheParkMC.TheParkMC.Events;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class EVT_Plugin implements PluginMessageListener{

	@Override
	public void onPluginMessageReceived(String channel, Player p, byte[] message)
	{
		if (!channel.equals("BungeeCord"))
		{
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (subchannel.equals("GetServers"))
		{
			String[] serverList = in.readUTF().split(", ");
			if(TheParkMC.getInstance().getBungeeManager().getServerNames().size() == 0 || TheParkMC.getInstance().getBungeeManager().getServerNames() == null)
			{
				for(String s : serverList)
				{
					TheParkMC.getInstance().getBungeeManager().getServerNames().add(s);
					if(s.contains("cw"))
						TheParkMC.getInstance().getBungeeManager().getCWServerNames().add(s);
					else if(s.contains("sg"))
						TheParkMC.getInstance().getBungeeManager().getSGServerNames().add(s);
					else if(s.contains("m"))
						TheParkMC.getInstance().getBungeeManager().getMurderServerNames().add(s);
					else if(s.contains("ha"))
						TheParkMC.getInstance().getBungeeManager().getHAServerNames().add(s);
					else if(s.contains("k"))
						TheParkMC.getInstance().getBungeeManager().getKingsServerNames().add(s);
				}
				TheParkMC.getInstance().getBungeeManager().sortServers(p);
			}
		}
		else if(subchannel.equals("PlayerCount"))
		{
			String server = in.readUTF();
			int playercount = in.readInt();
			TheParkMC.getInstance().getBungeeManager().getServerPlayerAmounts().put(server, playercount);
		}
		else if(subchannel.equals("ServerIP"))
		{
			String servername = in.readUTF();
			System.out.println(TheParkMC.getInstance().getStarter() + servername);
			short port = in.readShort();
			System.out.println(TheParkMC.getInstance().getStarter() + String.valueOf(port));
			String ip = in.readUTF();
			System.out.println(TheParkMC.getInstance().getStarter() + ip);
			TheParkMC.getInstance().getBungeeManager().getServerNamesAndIPs().put(servername, port);
		}
	}

}
