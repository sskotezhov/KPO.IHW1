package main.java.dataclasses;

import main.java.interfaces.Typed;
import main.java.interfaces.Type;

import java.util.Date;
import java.util.UUID;


public class Operation extends DataClass implements Typed {
	private Type type;//properties
	private UUID bank_account_id;
	private Double amount;
	private Date date;
	private String description;
	private UUID category_id;
	
	
	Operation(UUID id,
			  Type type,
			  UUID bank_account_id,
			  Double amount,
			  Date date,
			  String description,
			  UUID category_id)//Constructor
	{
		this.id = id;
		this.type = type;
		this.bank_account_id = bank_account_id;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.category_id = category_id;
	}
	
	
	public Type getType()//getters
	{
		return type;
	}
	public UUID getBank_account_id()
	{
		return bank_account_id;
	}
	public Double getAmount()
	{
		return amount;
	}
	public Date getDate()
	{
		return date;
	}
	public String getDescription()
	{
		return description;
	}
	public UUID getCategory_id()
	{
		return category_id;
	}
	
	
	
	public void setType(Type type)//setters
	{
		this.type = type;
	}
	public void setBank_account_id(UUID id)
	{
		this.bank_account_id = id;
	}
	public void setAmount(Double amount)
	{
		this.amount = amount;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public void setCategory_id(UUID id)
	{
		this.category_id = id;
	}
	
	
	
	@Override
	public String dataToStr()
	{
		String response = id.toString() + ";" + type.toString() + ";" + bank_account_id.toString() + ";"
						+ amount.toString() + ";" + date.toString() + ";" + category_id.toString() + ";" + description;
		return response;
	}
}
