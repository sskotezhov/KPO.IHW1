package main.java.dataclasses;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.java.interfaces.Type;
import main.java.tables.*;

@Component
@Scope("singleton")
public class Fabric {
	@Autowired
	private BankAccountTable tableB;
	@Autowired
	private CategoriesTable tableC;
	@Autowired
	private OperationTable tableO;
	Fabric(){}
	
	public BankAccount createBankAccount(String name, Double balance)
	{
		return new BankAccount(getNotUsedId(), name, balance);
	}
	
	
	public Categories createCategory(String name, Type type)
	{
		return new Categories(getNotUsedId(), name, type);
	}
	
	
	public Operation createOperation(Type type,
									UUID bank_account_id,
									Double amount,
									Date date,
									String description,
									UUID category_id)
	{
		if (amount < 0) return null;
		return new Operation(getNotUsedId(), type, bank_account_id, amount, date, description, category_id);
	}
	
	
	private UUID getNotUsedId()
	{
		UUID id;
		do
		{
			id = UUID.randomUUID();
		} while(containId(id));
		return id;
	}
	
	
	public boolean containId(UUID id)
	{
		return (tableB.get(id) != null ||
				tableC.get(id) != null ||
				tableO.get(id) != null);
	}
}
