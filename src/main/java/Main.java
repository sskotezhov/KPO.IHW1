package main.java;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.java.dataclasses.BankAccount;
import main.java.dataclasses.Fabric;
import main.java.tables.BankAccountTable;



@SpringBootApplication
public class Main implements CommandLineRunner{
    private static Logger LOG = LoggerFactory
    	      .getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(Main.class, args);
        LOG.info("APPLICATION FINISHED");
    }
    
    @Autowired
    private BankAccountTable tableB;
    
    @Autowired
    private Fabric F;
    
    @Autowired
    private Command command;
    
    @Override
    public void run(String... args) {
    	System.out.println(tableB.getClass());
    	Scanner in = new Scanner(System.in);
    	System.out.println("Input a сommand: ");
        while (in.hasNextLine())
        {
        	try
        	{
        	String line = in.nextLine();
        	if (line == "exit")
        	{
        		break;
        	}
        	if (line.length() - line.replace(" ", "").length() != 2)
        	{
        		System.out.println("Wrong input");
        	}
        	else
        	{
        		String[] tmp = line.split(" ");
        		command.set(tmp[0], tmp[1], tmp[2]);
        		command.execute();
        	}
        	System.out.println("Input a сommand: ");
        	}
        	catch (Exception e)
        	{
        		System.out.println("Чета пошло не так... " + e);
        	}
        }
        
        
        
    	//table.put(new BankAccount(UUID.randomUUID(), "second", 100.d));
    	//table.print();
    }
}
