package me.TheParkMC.TheParkMC.Objects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.TheParkMC.TheParkMC.TheParkMC;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerPet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<Pet> pet;
	
	public PlayerPet()
	{
		pet = new ArrayList<Pet>();
	}
	
	@SuppressWarnings("deprecation")
	public Player getPlayer()
    {
        return Bukkit.getServer().getPlayerExact(name);
    }     
    
    public static PlayerPet getPlayerPet(String name)
    {
        return TheParkMC.getInstance().getPlayerPets().get(name);
    }
      
    
    public static PlayerPet getPlayerPet(Player player)
    {
        return TheParkMC.getInstance().getPlayerPets().get(player.getName());
    }

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Pet> getPets()
	{
		return pet;
	}
	
	public void save()
	{
		try
        {
            File f = new File(TheParkMC.getInstance().getDataFolder() + "/players/" + name + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(this);
            oos.close();
            System.out.println("Saved PetPlayer data: " + this.getName());
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.out.println("Error saving PET player: " + this.getName() + "!");
        }
	}

}
