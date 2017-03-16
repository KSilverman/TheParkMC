package me.TheParkMC.TheParkMC.Objects;

import org.bukkit.entity.Entity;

public class Pet {
	
	Entity entity;
	String name;
	String owner;
	
	public Pet(Entity entity, String name, String owner)
	{
		this.entity = entity;
		this.name = name;
		this.owner = owner;
	}
	
	public Entity getEntity()
	{
		return entity;
	}

	public void setEntity(Entity entity)
	{
		this.entity = entity;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getOwner()
	{
		return owner;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

}
