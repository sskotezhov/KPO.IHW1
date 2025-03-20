package main.java.tables;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.java.dataclasses.Categories;
import main.java.interfaces.Type;

@Component
@Scope("singleton")
public class CategoriesTable extends Table<Categories>{
	public CategoriesTable() {}
	
	public boolean edit(UUID id, String[] args)
	{
		if (table.get(id) == null)
			System.out.println("Элемент не найден");
		else
		{
			if (args.length != 2)
				System.out.println("Неверное количество аргументов");
			else
			{
				try
				{
					table.get(id).setType(Type.valueOf(args[1]));
					table.get(id).setName(args[0]);
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Тип должен быть одним из двух вариантов: income/expense");
				}
			}
		}
		return false;
	}
}
