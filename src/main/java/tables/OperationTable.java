package main.java.tables;

import java.util.Date;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.java.dataclasses.Operation;
import main.java.interfaces.Type;
import main.java.parser.checker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
@Scope("singleton")
public class OperationTable extends Table<Operation>{
	public OperationTable() {}
	
	public boolean edit(UUID id, String[] args)
	{
		if (table.get(id) == null)
			System.out.println("Элемент не найден");
		else
		{
			if (args.length != 6)
				System.out.println("Неверное количество аргументов");
			else
			{
				try
				{
					table.get(id).setType(Type.valueOf(args[0]));
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Тип должен быть одним из двух вариантов: income/expense");
				}
				
				try
				{
					table.get(id).setBank_account_id(UUID.fromString(args[1]));
				}
				catch(Exception e)
				{
					System.out.println("Плохое айди аккаунта банка: " + e);
				}
				
				try
				{
					if (!checker.isNumeric(args[2])) throw new IllegalArgumentException("Не число");
					table.get(id).setAmount(Double.parseDouble(args[2]));
				}
				catch(Exception e)
				{
					System.out.println("Плохая сумма операции: " + e);
				}
				
				try
				{
					DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy,HH:mm:ss");
					Date date = formatter.parse(args[3]);
					table.get(id).setDate(date);
				}
				catch (Exception e)
				{
					System.out.println("Дата должна быть формата dd.MM.yyyy,HH:mm:ss");
				}
				
				table.get(id).setDescription(args[4]);
				
				try
				{
					table.get(id).setCategory_id(UUID.fromString(args[5]));
				}
				catch (Exception e)
				{
					System.out.println("Неверное айди категории: " + e);
				}
			}
		}
		return false;
	}
}
