package me.TheParkMC.TheParkMC.Objects;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R3.EntityInsentient;

public class PlayerPetManager {
	
	public PlayerPetManager()
	{
		
	}
	
	public boolean hasNoPets(Player p)
	{
		PlayerPet pp = PlayerPet.getPlayerPet(p);
		if(pp.getPets().size() == 0 || pp.getPets().isEmpty())
			return true;
		return false;
	}
	
	public boolean petOrPetsAreTooFarAway(Player p)
	{
		PlayerPet pp = PlayerPet.getPlayerPet(p);
		if(!hasNoPets(p))
		{
			for(Pet le : pp.getPets())
			{
				if(getDistance(le.getEntity().getLocation(), p.getLocation()) > 5)
					return true;
			}
		}
		return false;
	}
	
	public void addPet(Player p, Pet pet)
	{
		PlayerPet pp = PlayerPet.getPlayerPet(p);
		pp.getPets().add(pet);
		p.sendMessage(TheParkMC.getInstance().getStarter() + ChatColor.BLUE + "Added " + ChatColor.GOLD + pet.getName() + ChatColor.BLUE + " who is a " + getPetNameAndColor(pet) + ChatColor.BLUE + " to your pet list!");
	}
	
	public String getPetNameAndColor(Pet pet) 
	{
		String name = pet.getName();
		if(pet.getEntity().getType() == EntityType.PIG)
			return ChatColor.LIGHT_PURPLE + name;
		else if(pet.getEntity().getType() == EntityType.CHICKEN)
			return ChatColor.GRAY + name;
		else if(pet.getEntity().getType() == EntityType.COW)
			return ChatColor.RED + name;
		else if(pet.getEntity().getType() == EntityType.MUSHROOM_COW)
			return ChatColor.DARK_RED + name;
		else if(pet.getEntity().getType() == EntityType.OCELOT)
			return ChatColor.YELLOW + name;
		else if(pet.getEntity().getType() == EntityType.HORSE)
			return ChatColor.GREEN + name;
		else if(pet.getEntity().getType() == EntityType.SHEEP)
			return ChatColor.WHITE + name;
		else if(pet.getEntity().getType() == EntityType.GIANT)
			return ChatColor.DARK_GREEN + name;
		return ChatColor.GOLD + name;
	}
	
	public ChatColor getPetNameColor(Pet pet)
	{
		if(pet.getEntity().getType() == EntityType.PIG)
			return ChatColor.LIGHT_PURPLE ;
		else if(pet.getEntity().getType() == EntityType.CHICKEN)
			return ChatColor.GRAY ;
		else if(pet.getEntity().getType() == EntityType.COW)
			return ChatColor.RED ;
		else if(pet.getEntity().getType() == EntityType.MUSHROOM_COW)
			return ChatColor.DARK_RED ;
		else if(pet.getEntity().getType() == EntityType.OCELOT)
			return ChatColor.YELLOW ;
		else if(pet.getEntity().getType() == EntityType.HORSE)
			return ChatColor.GREEN ;
		else if(pet.getEntity().getType() == EntityType.SHEEP)
			return ChatColor.WHITE ;
		else if(pet.getEntity().getType() == EntityType.GIANT)
			return ChatColor.DARK_GREEN ;
		return ChatColor.GOLD ;
	}

	public void teleportPetsToOwner(Player p)
	{
		PlayerPet pp = PlayerPet.getPlayerPet(p);
		for(Pet pet : pp.getPets())
			pet.getEntity().teleport(p);
	}
	
	public void followOwner(Player p, float speed)
	{
		PlayerPet pp = PlayerPet.getPlayerPet(p);
		if(petOrPetsAreTooFarAway(p))
		{
			for(Pet pet : pp.getPets())
			{
				if(getDistance(pet.getEntity().getLocation(), p.getLocation()) > 5)
					((EntityInsentient)pet.getEntity()).getNavigation().a(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), speed);
			}
		}
	}
	
	public void clearPlayerPets(Player p)
	{
		PlayerPet pp = PlayerPet.getPlayerPet(p);
		for(Pet pet : pp.getPets())
			pet.getEntity().remove();
	}
	
	public void spawnPlayerPets(Player p)
	{
		PlayerPet pp = PlayerPet.getPlayerPet(p);
		for(Pet pet : pp.getPets())
			p.getWorld().spawnEntity(p.getLocation(), pet.getEntity().getType());
	}
	
	private double getDistance(Location from, Location to)
    {
        return from.distanceSquared(to);
    }

}
