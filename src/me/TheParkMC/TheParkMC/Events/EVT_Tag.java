package me.TheParkMC.TheParkMC.Events;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

public class EVT_Tag implements Listener{

	@EventHandler
	public void onReceiveNameTag(AsyncPlayerReceiveNameTagEvent event)
	{
		event.setTag(TheParkMC.getInstance().getRankManager().getRankColor(event.getNamedPlayer()) + event.getNamedPlayer().getName());
	}

}
