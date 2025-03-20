package main.java.dataclasses;



import java.util.UUID;

import main.java.interfaces.Named;
import main.java.interfaces.Typed;
import main.java.interfaces.Type;


public class Categories extends DataClass implements Named, Typed {
	private String name;
	private Type type;
	
	
	Categories(UUID id, String name, Type type)
	{
		this.name = name;
		this.id = id;
		this.type = type;
	}
	
	
	public String getName()
	{
		return name;
	}
	public Type getType()
	{
		return type;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setType(Type type)
	{
		this.type = type;
	}
	
	@Override 
	public String dataToStr()
	{
		String response = id.toString() + ";" + name + ";" + type.toString();
		return response;
	}
}
