package me.TheParkMC.TheParkMC.Events;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class EVT_Interact implements Listener{

	private int taskID;

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		try
		{
			if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))
			{
				if (event.getPlayer().getItemInHand().getType() == Material.COMPASS) 
				{
					event.setCancelled(true);
					openCompass(event.getPlayer());
				}
				else if(event.getPlayer().getItemInHand().getType() == Material.CHEST)
				{
					event.setCancelled(true);
					openToys(event.getPlayer());
				}
				else if(event.getPlayer().getItemInHand().getType() == Material.TRAPPED_CHEST)
				{
					event.setCancelled(true);
					goBackToHubMenu(event.getPlayer());
				}
				else if(event.getPlayer().getItemInHand().getType() == Material.LEATHER_HELMET)
				{
					event.setCancelled(true);
					doPropellerHat(event.getPlayer());
				}
				else if(event.getPlayer().getItemInHand().getType() == Material.BED)
				{
					event.setCancelled(true);
					groundPropellerHat(event.getPlayer());
				}
				else if(event.getPlayer().getItemInHand().getType() == Material.EGG)
				{
					event.setCancelled(true);
					openPetMenu(event.getPlayer());
				}
				else if(event.getPlayer().getItemInHand().getType() == Material.SULPHUR)
				{
					event.setCancelled(true);
					if(TheParkMC.getInstance().getRankManager().isRanked(event.getPlayer()))
						openSulphur(event.getPlayer());
					else
						event.getPlayer().sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "You must be ranked to use speed! Go to " + "\nhttp://www.theparkmc.com/shop to buy a rank!");
				}
				else if(event.getPlayer().getItemInHand().getType() == Material.REDSTONE)
				{
					event.setCancelled(true);
					for (PotionEffect effect : event.getPlayer().getActivePotionEffects())
						event.getPlayer().removePotionEffect(effect.getType());
					event.getPlayer().getInventory().setItem(4, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.SULPHUR), "Speed", ChatColor.LIGHT_PURPLE));
				}
				else if(event.getPlayer().getItemInHand().getType() == Material.BLAZE_POWDER)
				{
					event.setCancelled(true);
					doBlazePowder(event.getPlayer());
				}
				else if(event.getPlayer().getItemInHand().getType() == Material.FIRE)
				{
					event.setCancelled(true);
					doFireDance(event.getPlayer());
				}
			}
		}
		catch(Exception e)
		{

		}
	}

	private void doFireDance(Player p)
	{
		if(TheParkMC.getInstance().getCooldownPlayers().containsKey(p.getName()))
		{
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "You have " + ChatColor.GRAY + String.valueOf(TheParkMC.getInstance().getCooldownPlayers().get(p.getName())) + ChatColor.RED + "until you can toggle players again!");
			return;
		}
		for(Player pp : Bukkit.getOnlinePlayers())
			p.showPlayer(pp);
		p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GOLD + "All players are now visible!");
		p.getInventory().setItem(8, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.BLAZE_POWDER), "Hide Players", ChatColor.DARK_RED));
	}

	private void doBlazePowder(final Player p)
	{
		if(TheParkMC.getInstance().getCooldownPlayers().containsKey(p.getName()))
		{
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "You have " + ChatColor.GRAY + String.valueOf(TheParkMC.getInstance().getCooldownPlayers().get(p.getName())) + ChatColor.RED + "until you can toggle players again!");
			return;
		}
		for(Player pp : Bukkit.getOnlinePlayers())
			p.hidePlayer(pp);
		p.getInventory().setItem(8, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.FIRE), "Show Players", ChatColor.RED));
		if(!TheParkMC.getInstance().getRankManager().modUp(p))
		{
			TheParkMC.getInstance().getCooldownPlayers().put(p.getName(), 15);
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GREEN + "Players successfully toggled! You have " + ChatColor.GRAY + "15 " + ChatColor.GREEN + "seconds until you can toggle again!");
			taskID = TheParkMC.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(TheParkMC.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{				
					if(p.isOnline())
					{
						TheParkMC.getInstance().getCooldownPlayers().put(p.getName(), TheParkMC.getInstance().getCooldownPlayers().get(p.getName()) - 1);
						if(TheParkMC.getInstance().getCooldownPlayers().get(p.getName()) == 0)
						{
							TheParkMC.getInstance().getCooldownPlayers().remove(p.getName());
							p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GREEN + "You can toggle players again!");
							TheParkMC.getInstance().getServer().getScheduler().cancelTask(taskID);
							taskID = 0;
						}
					}
					else
					{
						TheParkMC.getInstance().getServer().getScheduler().cancelTask(taskID);
						taskID = 0;
					}
				}
			}, 0L, 20L);
		}
		else
		{
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GREEN + "Players successfully toggled!");
		}
	}

	private void openSulphur(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " *SPEED*");
		inv.setItem(0, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.COAL), ChatColor.GREEN + "§lWimpy", ChatColor.GREEN + "For the weakest of them all!"));
		inv.setItem(2, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.IRON_INGOT), ChatColor.YELLOW + "§lAmeture", ChatColor.YELLOW + "Still a rookie..."));
		inv.setItem(4, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.GOLD_INGOT), ChatColor.RED + "§lAverage", ChatColor.RED + "Getting there..."));
		inv.setItem(6, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.DIAMOND), ChatColor.DARK_PURPLE + "§lAdvanced", ChatColor.DARK_PURPLE + "Impressed."));
		inv.setItem(8, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.EMERALD), ChatColor.DARK_RED + "§lINSANE", ChatColor.DARK_RED + "Touch this if you dare!"));
		p.openInventory(inv);
	}
	
	private void openPetMenu(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BLUE + "" + ChatColor.BOLD + " *PETS*");
		inv.setItem(1, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.EMERALD), "Add Pet", ChatColor.GREEN));
		inv.setItem(3, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.REDSTONE), "Remove Pet", ChatColor.RED));
		inv.setItem(5, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.PAINTING), "Show Pet(s)", ChatColor.AQUA));
		inv.setItem(7, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.CHEST), "Hide Pet(s)", ChatColor.GOLD));
		p.openInventory(inv);
	}
	
	private void doPropellerHat(Player p)
	{
		p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.BOLD + "You now have a propeller hat on your head! Sneak to activiate!");
		p.getInventory().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) } );
		p.getInventory().setHelmet(TheParkMC.getInstance().getItemManager().setColorForLeather(new ItemStack(Material.LEATHER_HELMET), "Propeller Hat", Color.WHITE, ChatColor.DARK_AQUA));
		p.getInventory().setItem(1, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.BED), "Ground Propeller Hat Mode", ChatColor.AQUA));
		TheParkMC.getInstance().getPropellerHatPlayers().add(p.getName());
	}
	
	private void groundPropellerHat(Player p)
	{
		if(TheParkMC.getInstance().getPropellerHatPlayers().contains(p.getName()))
		{
			p.getInventory().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) } );
			p.getInventory().setItem(1, TheParkMC.getInstance().getItemManager().setColorForLeather(new ItemStack(Material.LEATHER_HELMET), "Propeller Hat", Color.WHITE, ChatColor.DARK_AQUA));
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GRAY + "" + ChatColor.BOLD + "You have removed your cap, so you have quit PROPELLER HAT mode!");
		}
	}

	private void openCompass(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "" + ChatColor.BOLD + " *QUICK TELEPORT*");
		inv.setItem(0, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.CAULDRON_ITEM), ChatColor.DARK_PURPLE + "Cauldron Wars", ChatColor.GOLD + "A fast paced, team-based CTF style game!"));
		inv.setItem(2, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.IRON_SWORD), ChatColor.GOLD + "Survival Games", ChatColor.RED + "Classic Survival Games with a ParkMC twist!"));
		inv.setItem(6, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.LADDER), ChatColor.BLUE + "Playground", ChatColor.LIGHT_PURPLE + "An assortment of fun and unique minigames!"));
		p.openInventory(inv);
	}
	
	private void openToys(Player p)
	{
		p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.YELLOW + "You have entered " + "§lTOYS" + ChatColor.YELLOW + " mode!");
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) } );
		p.getInventory().setItem(0, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.EGG), "Pet Menu", ChatColor.BLUE));
		p.getInventory().setItem(1, TheParkMC.getInstance().getItemManager().setColorForLeather(new ItemStack(Material.LEATHER_HELMET), "Propeller Hat", Color.WHITE, ChatColor.DARK_AQUA));
		p.getInventory().setItem(4, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.SULPHUR), "SPEED", ChatColor.LIGHT_PURPLE));
		p.getInventory().setItem(8, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.TRAPPED_CHEST), "Hub Menu", ChatColor.YELLOW));
	}
	
	private void goBackToHubMenu(Player p)
	{
		p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GOLD + "You have entered " + "§lHUB" + ChatColor.YELLOW + " mode!");
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) } );
		p.getInventory().setItem(0, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.COMPASS), "Quick Teleporter", ChatColor.GREEN));
		p.getInventory().setItem(1, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.GOLD_INGOT), "Shop", ChatColor.GOLD));
		p.getInventory().setItem(4, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.CHEST), "Toys", ChatColor.YELLOW));
		p.getInventory().setItem(8, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.BLAZE_POWDER), "Hide Players", ChatColor.DARK_RED));
	}

}
