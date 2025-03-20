package main.java.dataclasses;


import java.util.UUID;


import main.java.interfaces.Named;

public class BankAccount extends DataClass implements Named{
	private String name;
	private Double balance;
	
	public BankAccount(UUID id, String name, Double balance)
	{
		this.id = id;
		this.name = name;
		this.balance = balance;
	}
	
	public String getName()
	{
		return name;
	}
	public Double getBalance()
	{
		return balance;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setBalance(Double balance)
	{
		this.balance = balance;
	}
	
	@Override 
	public String dataToStr()
	{
		String response = id.toString() + ";" + name + ";" + balance.toString();
		return response;
	}
}
