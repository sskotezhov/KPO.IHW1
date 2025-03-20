package main.java.tables;


import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.java.dataclasses.BankAccount;

import main.java.parser.checker;

@Component
@Scope("singleton")
public class BankAccountTable extends Table<BankAccount> {
	public BankAccountTable() {}
	
	@Override
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
				if (!checker.isNumeric(args[1]))
					System.out.println("Неверный тип аргументов");
				else
				{
					table.get(id).setBalance((Double)Double.parseDouble(args[1]));
					table.get(id).setName(args[0]);
					return true;
				}
			}
		}
		return false;
	}
}
	
