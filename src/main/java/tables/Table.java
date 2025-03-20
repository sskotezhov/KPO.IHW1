package main.java.tables;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Component;

import main.java.dataclasses.DataClass;

@Component
public abstract class Table<V> {
	protected HashMap<UUID, V> table = new HashMap<UUID, V>();
	
	public V get(UUID id)
	{
		if (table.containsKey(id))
			return table.get(id);
		
		
		return null;
	}
	
	public boolean remove(UUID _id)
	{
		if (table.remove(_id) == null)
			return false;
		else
			return true;
	}
	
	public void put(V v)
	{
		DataClass elem = (DataClass)v;
		table.put(elem.getId(), v);
	}
	
	public void print()
	{
		for (var entry : table.entrySet()) {
		    System.out.println(((DataClass)entry.getValue()).dataToStr());
		}
	}
	
	public abstract boolean edit(UUID id, String[] args);
}
