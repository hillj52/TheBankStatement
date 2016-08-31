package com.ssa.tiy.bank;

import java.util.ArrayList;

public class mainline {
	
	public static void main(String[] args) {
		Investment i = new Investment("Robinhood Account");
		i.deposit(1000);
		i.purchaseStock("UA","Under Armour", 40.00, 10);
		i.purchaseStock("F", "Ford", 13.50, 10);
		i.purchaseStock("UA","Under Armour", 50.00, 2);
		i.purchaseStock("TSLA","Tesla", 100, 2);
		i.updateStockPrice("TSLA", 132.76);
		i.purchaseStock("AMD","Advaced Micro Designs", 2.5, 20);
		i.updateStockPrice("AMD", 7.43);
		i.updateStockPrice("F", 12.25);
		i.split("UA");
		
		Savings savings1 = new Savings();
		savings1.setDescription("Savings A");
		savings1.deposit(500.00);
		savings1.setInterestRate(0.025);

		Checking checking2 = new Checking();
		checking2.setDescription("Checking B");
		checking2.setLastCheckNum(100);
		checking2.deposit(400.00);

		Savings savings3 = new Savings();
		savings3.setDescription("Savings C");
		savings3.deposit(800.00);
		savings3.setInterestRate(0.015);

		Checking checking4 = new Checking();
		checking4.setDescription("Checking D");
		checking4.setLastCheckNum(230);
		checking4.deposit(200.00);

		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add(savings1);
		accounts.add(checking2);
		accounts.add(savings3);
		accounts.add(checking4);
		accounts.add(i);

		System.out.println("T Act  " + String.format("%-20s","Description") + 
				String.format("%-10s","Bal") + " Chk Rate Investment-Value");
		System.out.println("= ===  ===========         ======     === ==== ================");
		for(Account account : accounts) {
		    System.out.println(account.print());
		}
	}

}
