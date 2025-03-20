package main.java;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.java.dataclasses.BankAccount;
import main.java.dataclasses.DataClass;
import main.java.dataclasses.Fabric;
import main.java.interfaces.Type;
import main.java.tables.*;

@Component
@Scope("prototype")
public class Command {
	ArrayList<String> args;
	String table;
	String command;
	
	Command()
	{
	}
	
	public void set(String command, String table, String args)
	{
		this.args = new ArrayList<>(Arrays.asList(args.split(";")));
		this.command = command;
		this.table = table;
	}
	
	@Autowired
	private BankAccountTable tableB;
	@Autowired
	private CategoriesTable tableC;
	@Autowired
	private OperationTable tableO;
	@Autowired
	private Fabric fabric;
	
	public void execute()
	{
		Table<?> table_;
		switch (table) 
		{
			case ("BankAccount"):
			{
				table_ = tableB;
				break;
			}
			case ("Operation"):
			{
				table_ = tableO;
				break;
			}
			case ("Category"):
			{
				table_ = tableC;
				break;
			}
			default:
			{
				System.out.println("Неправильный тип таблицы");
				return;
			}
		}
		switch (command)
		{
			case ("remove"):
			{
				if (args.size() != 1)
				{
					System.out.println("Неверное количество аргументов");
				}
				else
				{
					try
					{
						UUID id = UUID.fromString(args.get(0));
						if (!table_.remove(id)) System.out.println("Аргумент не найден");
					}
					catch (Exception e)
					{
						System.out.println("Неправильный тип аргумента: " + e);
					}
				}
				break;
			}
			case ("add"):
			{
				if (table_ instanceof BankAccountTable) { // тут нельзя юзнуть кейс, тк проверка на экземпляр класса не позволяет это сделать, а она необходима дабы избежать неопределнное поведение
	                BankAccountTable accountTable = (BankAccountTable) table_;
	                try
	                {
	                	accountTable.put(fabric.createBankAccount(args.get(0), Double.parseDouble(args.get(1))));
	                }
	                catch (Exception e)
	                {
	                	System.out.println("Неправильный тип аргументов: " + e);
	                }
	            }
				
				
				if (table_ instanceof OperationTable) { 
	                OperationTable accountTable = (OperationTable) table_;
	                Date date;
					try
					{
						DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy,HH:mm:ss");
						date = formatter.parse(args.get(3));
						
		                try
		                {
		                	
		                	accountTable.put(fabric.createOperation(Type.valueOf(args.get(0)), 
		                					 UUID.fromString(args.get(1)), Double.parseDouble(args.get(2)),
		                					 date, args.get(4), UUID.fromString(args.get(5))));
		                }
		                catch (Exception e)
		                {
		                	System.out.println("Неправильный тип аргументов: " + e);
		                }
					}
					catch (Exception e)
					{
						System.out.println("Дата должна быть формата dd.MM.yyyy,HH:mm:ss, например 20.03.2025,8:00:00");
					}
	            }
				
				
				if (table_ instanceof CategoriesTable) { 
					CategoriesTable accountTable = (CategoriesTable) table_;
					try
	                {
	                	
						accountTable.put(fabric.createCategory(args.get(0), Type.valueOf(args.get(1))));
	                }
	                catch (Exception e)
	                {
	                	System.out.println("Неправильный тип аргументов: " + e);
	                }
	            }
				break;
			}
			case ("edit"):
			{
				if (args.size() < 2)
				{
					System.out.println("Неверное количество аргументов");
				}
				else
				{
					UUID id;
					try
					{
						id = UUID.fromString(args.get(0));
						args.remove(0);
						String[] arr = new String[args.size()];
						args.toArray(arr);
						table_.edit(id, arr);
					}
					catch (Exception e)
					{
						System.out.println("Ошибка: " + e);
					}
				}
				break;
			}
			default:
			{
				System.out.println("Команда не найдена");
			}
		}
		table_.print();
		
	}
}
