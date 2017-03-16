package me.TheParkMC.TheParkMC.Events;

import me.TheParkMC.TheParkMC.TheParkMC;
import me.TheParkMC.TheParkMC.TheParkMCProxy;
import me.TheParkMC.TheParkMC.Objects.PlayerPet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EVT_InvClick implements Listener{
	
	@EventHandler
	public void onPlayerInvClick(InventoryClickEvent event)
	{
		if(!(event.getWhoClicked() instanceof Player))
		{
			return;
		}
		
		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);
		if(event.getInventory().getName().equals(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " *SPEED*"))
		{
			if(event.getCurrentItem().getType() == Material.COAL)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
				p.closeInventory();
			}
			else if(event.getCurrentItem().getType() == Material.IRON_INGOT)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
				p.closeInventory();
			}
			else if(event.getCurrentItem().getType() == Material.GOLD_INGOT)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
				p.closeInventory();
			}
			else if(event.getCurrentItem().getType() == Material.DIAMOND)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3));
				p.closeInventory();
			}
			else if(event.getCurrentItem().getType() == Material.EMERALD)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 10));
				p.closeInventory();
			}
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GOLD + "You chose the " + event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.GOLD + " speed!");
			p.getInventory().setItem(4, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.REDSTONE), "§lSPEED OFF", ChatColor.DARK_RED));
		}
		else if(event.getInventory().getName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + " *PETS*"))
		{
			if(event.getCurrentItem().getType() == Material.EMERALD)
				openAddPetMenu(p);
			else if(event.getCurrentItem().getType() == Material.REDSTONE)
				openRemovePetMenu(p);
			else if(event.getCurrentItem().getType() == Material.PAINTING)
				openShowPetMenu(p);
			else if(event.getCurrentItem().getType() == Material.CHEST)
				openHidePetMenu(p);
		}
		else if(event.getInventory().getName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + " *ADD PET*"))
		{
			
		}
		else if(event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + " *REMOVE PET*"))
		{
			
		}
		else if(event.getInventory().getName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + " *SHOW PET*"))
		{
			
		}
		else if(event.getInventory().getName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + " *HIDE PET*"))
		{
			
		}
		else if(event.getInventory().getName().equals(ChatColor.RED + "" + ChatColor.BOLD + " *QUICK TELEPORT*"))
		{
			if(event.getCurrentItem().getType() == Material.CAULDRON_ITEM)
			{
				openCWServerList(p);
			}
		}
		else if(event.getInventory().getName().equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + " *Cauldron Wars*"))
		{
			if(event.getCurrentItem().getType() == Material.REDSTONE_BLOCK)
			{
				p.closeInventory();
				p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.DARK_RED + "That server is offline!");
				return;
			}
			else if(event.getCurrentItem().getType() == Material.EMERALD_BLOCK)
			{
				try
				{
					TheParkMC.getInstance().getBungeeManager().sendToServer(event.getCurrentItem().getItemMeta().getDisplayName(), p);
				}
				catch(Exception e)
				{
					p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "There was a problem connecting you to " + event.getCurrentItem().getItemMeta().getDisplayName());
				}
				p.closeInventory();
			}
		}
	}
	
	private void openAddPetMenu(Player p)
	{
		if(PlayerPet.getPlayerPet(p).getPets().size() <= 1 && !TheParkMC.getInstance().getRankManager().isRanked(p))
		{
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "You must be ranked to have more than one pet! Go to " + "\nhttp://www.theparkmc.com/shop to buy a rank!");
			p.closeInventory();
		}
		else if(PlayerPet.getPlayerPet(p).getPets().size() <= 3 && !TheParkMC.getInstance().getRankManager().legendUp(p))
		{
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "You must be " + ChatColor.GOLD + "§lLEGEND " + ChatColor.RED + "or higher to have more than 3 pets! Go to " + "\nhttp://www.theparkmc.com/shop to buy a rank!");
			p.closeInventory();
		}
		else if(PlayerPet.getPlayerPet(p).getPets().size() <= 5 && !TheParkMC.getInstance().getRankManager().godUp(p))
		{
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "You must be " + ChatColor.AQUA + "§lGOD " + ChatColor.RED + "or higher to have more than 5 pets! Go to " + "\nhttp://www.theparkmc.com/shop to buy a rank!");
			p.closeInventory();
		}
		else if(PlayerPet.getPlayerPet(p).getPets().size() <= 50)
		{
			p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "You may only have a MAX of 50 pets! If you want new ones, remove them!");
			p.closeInventory();
		}
		else
		{
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "" + ChatColor.BOLD + " *ADD PET*");
			inv.setItem(0, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.ARROW), "MAIN PET MENU", ChatColor.BLUE));
			inv.setItem(1, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short) EntityType.PIG.ordinal()), "PIG", ChatColor.LIGHT_PURPLE));
			inv.setItem(2, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short) EntityType.CHICKEN.ordinal()), "CHICKEN", ChatColor.GRAY));
			inv.setItem(3, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short) EntityType.COW.ordinal()), "COW", ChatColor.RED));
			inv.setItem(4, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short) EntityType.MUSHROOM_COW.ordinal()), "MOOSHROOM COW", ChatColor.DARK_RED));
			inv.setItem(5, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short) EntityType.OCELOT.ordinal()), "OCELOT", ChatColor.YELLOW));
			inv.setItem(6, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short) EntityType.HORSE.ordinal()), "HORSE", ChatColor.GREEN));
			inv.setItem(7, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short) EntityType.SHEEP.ordinal()), "SHEEP", ChatColor.WHITE));
			inv.setItem(8, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short) EntityType.GIANT.ordinal()), "GIANT", ChatColor.DARK_GREEN));
			p.openInventory(inv);
		}
	}
	
	private void openRemovePetMenu(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, getPetSizeMap(p), ChatColor.RED + "" + ChatColor.BOLD + " *REMOVE PET*");
		inv.setItem(0, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.ARROW), "MAIN PET MENU", ChatColor.BLUE));
		for(int i = 1; i < PlayerPet.getPlayerPet(p).getPets().size(); i++)
		{
			inv.setItem(i, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short)PlayerPet.getPlayerPet(p).getPets().get(i).getEntity().getType().ordinal()),
					PlayerPet.getPlayerPet(p).getPets().get(i).getName(), TheParkMC.getInstance().getPlayerPetManager().getPetNameColor(PlayerPet.getPlayerPet(p).getPets().get(i))));
		}
		inv.setItem((getPetSizeMap(p)-1), TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.EGG), "ALL", ChatColor.AQUA));
		p.openInventory(inv);
	}
	
	private void openShowPetMenu(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, getPetSizeMap(p), ChatColor.AQUA + "" + ChatColor.BOLD + " *SHOW PET*");
		inv.setItem(0, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.ARROW), "MAIN PET MENU", ChatColor.BLUE));
		for(int i = 1; i < PlayerPet.getPlayerPet(p).getPets().size(); i++)
		{
			inv.setItem(i, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short)PlayerPet.getPlayerPet(p).getPets().get(i).getEntity().getType().ordinal()),
					PlayerPet.getPlayerPet(p).getPets().get(i).getName(), TheParkMC.getInstance().getPlayerPetManager().getPetNameColor(PlayerPet.getPlayerPet(p).getPets().get(i))));
		}
		inv.setItem((getPetSizeMap(p)-1), TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.EGG), "ALL", ChatColor.AQUA));
		p.openInventory(inv);
	}
	
	private void openHidePetMenu(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, getPetSizeMap(p), ChatColor.GOLD + "" + ChatColor.BOLD + " *HIDE PET*");
		inv.setItem(0, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.ARROW), "MAIN PET MENU", ChatColor.BLUE));
		for(int i = 1; i < PlayerPet.getPlayerPet(p).getPets().size(); i++)
		{
			inv.setItem(i, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.MONSTER_EGG, 1, (short)PlayerPet.getPlayerPet(p).getPets().get(i).getEntity().getType().ordinal()),
					PlayerPet.getPlayerPet(p).getPets().get(i).getName(), TheParkMC.getInstance().getPlayerPetManager().getPetNameColor(PlayerPet.getPlayerPet(p).getPets().get(i))));
		}
		inv.setItem((getPetSizeMap(p)-1), TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.EGG), "ALL", ChatColor.AQUA));
		p.openInventory(inv);
	}
	
	private void openCWServerList(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, getSizeForMap(), ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + " *Cauldron Wars*");
		
		for(int i = 0; i < getSizeForMap(); i++)
		{
			if(i < TheParkMC.getInstance().getBungeeManager().getCWServerNames().size())
			{
				String cwserver = TheParkMC.getInstance().getBungeeManager().getCWServerNames().get(i);
				TheParkMC.getInstance().getBungeeManager().requestServerIP(cwserver, p);
				if(TheParkMCProxy.getInstance().getServerStatus(cwserver))
				{
					inv.setItem(i, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.EMERALD_BLOCK, TheParkMC.getInstance().getBungeeManager().getPlayerCount(cwserver, p)), cwserver, ChatColor.GREEN + "" + ChatColor.BOLD + "ONLINE!"));
				}
				else
				{
					inv.setItem(i, TheParkMC.getInstance().getItemManager().setNameAndLore(new ItemStack(Material.REDSTONE_BLOCK), cwserver, ChatColor.DARK_RED + "" + ChatColor.BOLD + "OFFLINE!"));
				}
			}
			else
			{
				inv.setItem(i, TheParkMC.getInstance().getItemManager().setName(new ItemStack(Material.LAVA), "Server to come soon!", ChatColor.YELLOW));
			}
		}
		p.openInventory(inv);
	}
	
	private int getSizeForMap()
    {
        int size = TheParkMC.getInstance().getBungeeManager().getCWServerNames().size();
        while(!(size % 9 == 0))
        {
            ++size;
        }
        return (size > 54) ? 54 : size;
    }
	
	private int getPetSizeMap(Player p)
	{
		int size = (PlayerPet.getPlayerPet(p).getPets().size() + 1);
		while(!(size % 9 == 0))
		{
			++size;
		}
		return (size > 54) ? 54 : size;
	}

}
