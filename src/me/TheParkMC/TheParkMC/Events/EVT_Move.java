package me.TheParkMC.TheParkMC.Events;

import me.TheParkMC.TheParkMC.TheParkMC;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class EVT_Move implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if(event.getPlayer().getLocation().getY() < 38)
			event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 20.5D, 51.0D, 25.5D));
		
		if(TheParkMC.getInstance().getJumpedPlayers().contains(event.getPlayer().getName()) && event.getPlayer().isOnGround())
			TheParkMC.getInstance().getJumpedPlayers().remove(event.getPlayer().getName());
		
		if(TheParkMC.getInstance().getPlayerPetManager().petOrPetsAreTooFarAway(event.getPlayer()))
			TheParkMC.getInstance().getPlayerPetManager().followOwner(event.getPlayer(), 0.25F);
		
		if(TheParkMC.getInstance().getPropellerHatPlayers().contains(event.getPlayer().getName()))
		{
			if(event.getPlayer().getInventory().getHelmet().getType() == Material.LEATHER_HELMET)
			{
				if(event.getPlayer().isSneaking())
				{
					Vector jump = event.getPlayer().getLocation().getDirection().multiply(2.1).setY(1.1);
					event.getPlayer().setVelocity(event.getPlayer().getVelocity().add(jump));
				}
			}
			else
			{
				TheParkMC.getInstance().getPropellerHatPlayers().remove(event.getPlayer().getName());
				event.getPlayer().sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GRAY + "" + ChatColor.BOLD + "You have removed your cap, so you have quit PROPELLER HAT mode!");
			}
		}
	}

}
