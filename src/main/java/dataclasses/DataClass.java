package main.java.dataclasses;

import java.util.UUID;

import main.java.interfaces.Identifiable;

public abstract class DataClass implements Identifiable{
	protected UUID id;
	
	public UUID getId()
	{
		return id;
	}
	
	public void setId(UUID id)
	{
		this.id = id;
	}
	
	public abstract String dataToStr();
}
