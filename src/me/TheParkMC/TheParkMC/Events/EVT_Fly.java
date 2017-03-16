package me.TheParkMC.TheParkMC.Events;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.event.Listener;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class EVT_Fly implements Listener{

	@EventHandler
	public void setFlyOnJump(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();

		if(event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE)
		{

			if(TheParkMC.getInstance().getJumpedPlayers().contains(player.getName()))
			{
				event.setCancelled(true);
				return;
			}
			
			event.setCancelled(true);
			player.setFlying(false);
			Vector jump = player.getLocation().getDirection().multiply(2.1).setY(1.1);
			player.setVelocity(player.getVelocity().add(jump));
			TheParkMC.getInstance().getJumpedPlayers().add(player.getName());
		}
	}

}
