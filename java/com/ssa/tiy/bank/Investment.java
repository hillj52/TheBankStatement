package com.ssa.tiy.bank;

import java.util.ArrayList;
import java.util.HashMap;

public class Investment extends Account {
	
	private HashMap<String,ArrayList<Stock>> stocks;
	private ArrayList<String> symbols;
	
	public String print() {
		return "I " + super.toString() + String.format("%-10s","") + this.getInvestmentBalance() + 
				"\n" + "Portfolio Summary\n" + "=================\n" + this.printStocks();
	}
	
	public void split(String symbol) {
		if (symbols.contains(symbol)) {
			ArrayList<Stock> list = stocks.get(symbol);
			for (Stock stock : list) {
				stock.split();
			}
		}
	}
	
	public String printStocks() {
		String sb = String.format("%-8s","Sym") + String.format("%-25s", "Name") +
				String.format("%-10s", "Shares") + String.format("%-10s", "Cost") +
				String.format("%-10s", "Price") + String.format("%-10s", "Value") +
				String.format("%-10s","Gain/Loss") + "\n";
		for (String sym:symbols) {
			ArrayList<Stock> list = stocks.get(sym);
			for (Stock stock:list) {
				sb += stock.print() + "\n";
			}
		}
		return sb;
	}
	
	public double purchaseStock(String symbol,String name,double pricePerShare, int shares) {
		if(pricePerShare > 0 && shares > 0 && isValidSymbol(symbol) 
				&& (pricePerShare * shares) <= super.getBalance()) {
			if (symbols.contains(symbol)) {
				ArrayList<Stock> list = stocks.get(symbol);
				list.add(new Stock(symbol,name,pricePerShare,shares));
				for (Stock stock:list) {
					stock.setPrice(pricePerShare);
				}
			} else {
				symbols.add(symbol);
				ArrayList<Stock> list = new ArrayList<Stock>();
				list.add(new Stock(symbol,name,pricePerShare,shares));
				stocks.put(symbol,list);
			}
			super.withdraw(pricePerShare * shares);
		}
		return this.getBalance();
	}
	
	public double sellStock(String symbol, int shares) {
		if (symbols.contains(symbol) && getSharesOwned(symbol) >= shares  && shares > 0) {
			ArrayList<Stock> list = stocks.get(symbol);
			int sold = 0;
			double price = list.get(0).getPrice();
			for (Stock stock:list) {
				if (stock.getSharesOwned() > (shares - sold)) {
					sold += stock.getSharesOwned();
					stock.sell(stock.getSharesOwned());
				} else {
					stock.sell(shares - sold);
					sold = shares;
				}
			}
			this.deposit(shares * price);
		}
		return this.getBalance();
	}
	
	private int getSharesOwned(String symbol) {
		int total = 0;
		if (symbols.contains(symbol)) {
			ArrayList<Stock> list = stocks.get(symbol);
			for (Stock stock:list) {
				total += stock.getSharesOwned();
			}
		}
		return total;
	}
	
	public double updateStockPrice(String symbol, double newPrice) {
		if (symbols.contains(symbol)) {
			ArrayList<Stock> list = stocks.get(symbol);
			for (Stock stock:list) {
				stock.setPrice(newPrice);
			}
		}
		return this.getBalance();
	}
	
	public double getBalance() {
		return this.getCashBalance() + this.getInvestmentBalance();
	}
	
	public double getCashBalance() {
		return super.getBalance();
	}
	
	public double getInvestmentBalance() {
		double value = 0;
		for (String symbol:symbols) {
			ArrayList<Stock> list = stocks.get(symbol);
			for (Stock stock:list) {
				value += stock.getValue();
			}
		}
		return value;
	}
	
	private boolean isValidSymbol(String symbol) {
		return (symbol.length() >= 1 && symbol.length() <= 5);
	}
	
	public Investment(int id, String description) {
		super(id,description);
		this.stocks = new HashMap<String,ArrayList<Stock>>();
		this.symbols = new ArrayList<String>();
	}
	
	public Investment(String description) {
		this(idGenner++,description);
	}
	
	public Investment() {
		this (idGenner++,"");
	}
}
