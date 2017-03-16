package me.TheParkMC.TheParkMC.Events;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class EVT_Block implements Listener{

	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent event)
	{
		if(!TheParkMC.getInstance().getOverridedPlayers().contains(event.getPlayer().getName()))
			event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerBlockPlace(BlockPlaceEvent event)
	{
		if(!TheParkMC.getInstance().getOverridedPlayers().contains(event.getPlayer().getName()))
			event.setCancelled(true);
	}

	@EventHandler
	public void onHungerChange(FoodLevelChangeEvent e)
	{
		e.setCancelled(true); 
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e)
	{
		e.setCancelled(true);
	}
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) 
	{
		e.setCancelled(true);    
	}
}
