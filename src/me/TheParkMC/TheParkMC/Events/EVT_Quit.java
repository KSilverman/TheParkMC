package me.TheParkMC.TheParkMC.Events;

import java.util.ArrayList;
import java.util.List;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EVT_Quit implements Listener{
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event)
	{
		if(TheParkMC.getInstance().getRankManager().modUp(event.getPlayer()))
			event.setQuitMessage(getTextBlock(3, ChatColor.DARK_RED) + " " + TheParkMC.getInstance().getRankManager().getRankColor(event.getPlayer()) + "§l" + event.getPlayer().getName() + ChatColor.RED + "§l" + " HAS LEFT THE PARK! " + getTextBlock(3, ChatColor.DARK_RED));
		else
			event.setQuitMessage(null);
		if(TheParkMC.getInstance().getOverridedPlayers().contains(event.getPlayer().getName()))
			TheParkMC.getInstance().getOverridedPlayers().remove(event.getPlayer().getName());
		if(TheParkMC.getInstance().getJumpedPlayers().contains(event.getPlayer().getName()))
			TheParkMC.getInstance().getJumpedPlayers().remove(event.getPlayer().getName());
		if(TheParkMC.getInstance().getPropellerHatPlayers().contains(event.getPlayer().getName()))
			TheParkMC.getInstance().getPropellerHatPlayers().remove(event.getPlayer().getName());
		if(TheParkMC.getInstance().getScorebobardScroller().toScroll.containsKey(event.getPlayer()))
			TheParkMC.getInstance().getScorebobardScroller().toScroll.remove(event.getPlayer());
		event.getPlayer().getActivePotionEffects().clear();
	}

	private String getTextBlock(int amount, ChatColor color)
	{
		List<String> temp = new ArrayList<String>();
		for(int i = 0; i < amount; i++)
			temp.add(color + "\u2588");
		String result = "";
		for(String s : temp)
			result += s;
		return result;
	}
	
}
