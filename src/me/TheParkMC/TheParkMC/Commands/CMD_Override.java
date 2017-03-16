package me.TheParkMC.TheParkMC.Commands;

import me.TheParkMC.TheParkMC.TheParkMC;
import me.TheParkMC.TheParkMC.Commands.SimpleCommand.CommandHandler;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Override {
	
	@CommandHandler(name = "override")
    public void onCommand(CommandSender sender, String[] args)
    {
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			if(TheParkMC.getInstance().getRankManager().modUp(p))
			{
				if(TheParkMC.getInstance().getOverridedPlayers().contains(p.getName()))
				{
					TheParkMC.getInstance().getOverridedPlayers().remove(p.getName());
					p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "Sucessfully " + ChatColor.DARK_RED + "REMOVED " + ChatColor.RED + "from override!");
				}
				else
				{
					TheParkMC.getInstance().getOverridedPlayers().add(p.getName());
					p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.GREEN + "Sucessfully " + ChatColor.DARK_GREEN + "ADDED" + ChatColor.GREEN + "to override!");
				}
			}
			else
			{
				p.sendMessage(TheParkMC.getInstance().getStarter() + TheParkMC.getInstance().getPermMsg());
			}
		}
		else
		{
			sender.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.RED + "You must be a player to use this command!");
		}
    }

}
