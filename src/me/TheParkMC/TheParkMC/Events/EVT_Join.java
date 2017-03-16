package me.TheParkMC.TheParkMC.Events;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.TheParkMC.TheParkMC.TheParkMC;
import me.TheParkMC.TheParkMC.TheParkMCProxy;
import me.TheParkMC.TheParkMC.Bungee.TestRunnable;
import me.TheParkMC.TheParkMC.Objects.PlayerPet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.theparkmc.API.scoreboard.ScoreboardFactory;
import com.theparkmc.API.scoreboard.ScrollingText;

public class EVT_Join implements Listener{
	
	private boolean ian = false;
	private boolean kyle = false;
	
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event)
	{
		TheParkMC.getInstance().getMySQLManager().givePlayerRank(event.getPlayer());
		if(TheParkMC.getInstance().getRankManager().modUp(event.getPlayer()))
			event.setJoinMessage(getTextBlock(3, ChatColor.DARK_GREEN) + " " + TheParkMC.getInstance().getRankManager().getRankColor(event.getPlayer()) + "§l" + event.getPlayer().getName() + ChatColor.YELLOW + "§l" + " HAS JOINED THE PARK! " + getTextBlock(3, ChatColor.DARK_GREEN));
		else
			event.setJoinMessage(null);
		event.getPlayer().setMaxHealth(20.0D);
		event.getPlayer().setAllowFlight(true);
		event.getPlayer().setPlayerListName(TheParkMC.getInstance().getRankManager().getRankColor(event.getPlayer()) + event.getPlayer().getName());
		event.getPlayer().getInventory().clear();
		giveLobbyGoodies(event.getPlayer());
		for (PotionEffect effect : event.getPlayer().getActivePotionEffects())
	        event.getPlayer().removePotionEffect(effect.getType());
		ScoreboardFactory.scoreboardFactory(event.getPlayer(), getScoreboardText(event.getPlayer()), TheParkMC.getInstance().getScorebobardScroller(), ChatColor.BLUE + "" + ChatColor.BOLD + "TheParkMC");
		if(!TheParkMC.getInstance().getPlayerPets().containsKey(event.getPlayer().getName()))
		{
			PlayerPet pp = new PlayerPet();
			pp.setName(event.getPlayer().getName());
			pp.save();
			TheParkMC.getInstance().getPlayerPets().put(pp.getName(), pp);
		}
		TheParkMC.getInstance().getServer().getScheduler().runTaskLater(TheParkMC.getInstance(), new Runnable()
		{
			public void run()
			{
				if(!ian)
				{
					TheParkMC.getInstance().getBungeeManager().requestServerNames(event.getPlayer());
					TheParkMCProxy.getInstance().getProxy().getScheduler().schedule(TheParkMCProxy.getInstance(), new TestRunnable(TheParkMCProxy.getInstance()), 0L, 5L, TimeUnit.SECONDS);
					ian = true;
				}
			}
		}, 2L);
		
		TheParkMC.getInstance().getServer().getScheduler().runTaskLater(TheParkMC.getInstance(), new Runnable()
		{
			public void run()
			{
				if(!kyle)
				{
					for(final String s : TheParkMC.getInstance().getBungeeManager().getCWServerNames())
					{
						TheParkMC.getInstance().getServer().getScheduler().runTaskLater(TheParkMC.getInstance(), new Runnable()
						{
							@Override
							public void run()
							{
								TheParkMC.getInstance().getBungeeManager().requestServerIP(s, event.getPlayer());
							}						
						}, 2L);
					}
					kyle = true;
				}
			}
		}, 6L);
		
	}

	@SuppressWarnings("deprecation")
	private ArrayList<ScrollingText> getScoreboardText(Player p)
	{
		ArrayList<ScrollingText> text = new ArrayList<ScrollingText>();
		text.add(new ScrollingText(ChatColor.BOLD + "MOTD", ChatColor.GREEN, false));
	    text.add(new ScrollingText("Get all the latest news and fun stuff on our site!", ChatColor.GOLD, true));
	    text.add(new ScrollingText(" ", ChatColor.GOLD, false));
	    text.add(new ScrollingText(ChatColor.BOLD + "Your Rank", ChatColor.BLUE, false));
	    text.add(new ScrollingText(TheParkMC.getInstance().getRankManager().getRankName(p.getName()), TheParkMC.getInstance().getRankManager().getRankColor(p), false));
	    text.add(new ScrollingText(" ", ChatColor.RED, false));
	    text.add(new ScrollingText(ChatColor.BOLD + "Staff Online", ChatColor.AQUA, false));
	    text.add(new ScrollingText(TheParkMC.getInstance().getRankManager().getHighestOnlineStaffMember(), TheParkMC.getInstance().getRankManager().getRankColor(Bukkit.getPlayer(TheParkMC.getInstance().getRankManager().getHighestOnlineStaffMember())), false));
	    text.add(new ScrollingText(" ", ChatColor.WHITE, false));
	    text.add(new ScrollingText(ChatColor.BOLD + "Our Website", ChatColor.DARK_GREEN, false));
	    text.add(new ScrollingText("Check out TheParkMC.com for ranks, news, and more!", ChatColor.GREEN, true));
	    text.add(new ScrollingText("--------------", ChatColor.WHITE, false));
		return text;
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
	
	private void giveLobbyGoodies(Player p)
	{
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) } );
		p.getInventory().setItem(0, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.COMPASS), "Quick Teleporter", ChatColor.GREEN));
		p.getInventory().setItem(1, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.GOLD_INGOT), "Shop", ChatColor.GOLD));
		p.getInventory().setItem(4, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.CHEST), "Toys", ChatColor.YELLOW));
		p.getInventory().setItem(8, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.BLAZE_POWDER), "Hide Players", ChatColor.DARK_RED));
	}
}
