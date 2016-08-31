package com.ssa.tiy.bank;

import java.util.Date;

public class Stock {

	private double cashFromSales;
	private double price;
	private double purchasePrice;
	private int sharesOwned;
	private int origShares;
	private Date datePurchased;
	private Date dateSold;
	private String symbol;
	private String name;
	
	public String print() {
		return this.toString();
	}
	
	public String toString() {
		return String.format("%-8s", this.getSymbol()) + String.format("%-25s", this.getName()) +
				String.format("%-10s", this.getSharesOwned()) + String.format("%-10s", this.purchasePrice) +
				String.format("%-10s", this.getPrice()) + String.format("%-10s",this.getValue()) +
				String.format("%-10s",this.getProfitOrLoss());
	}
	
	public String getProfitOrLoss() {
		double profit = (cashFromSales + (price * sharesOwned)) - (purchasePrice * origShares);
		return (profit >= 0 ? " " : "") + String.format("%.2f",profit);
	}

	public double getValue() {
		return (this.getPrice() * this.getSharesOwned());
	}
	
	public boolean hasSold() {
		return !(this.dateSold == null);
	}
	
	//Returns the number of days the stock has been owned
	public int getDaysOwned() {
		if(this.hasSold())
			return dateSold.compareTo(datePurchased);
		return (new Date()).compareTo(datePurchased);
	}
	
	public double sell(int shares) {
		if(shares > 0 && shares <= sharesOwned) {
			this.sharesOwned -= shares;
			this.setDateSold();
			this.cashFromSales += (shares * price);
			return shares * price;
		}
		return 0;
	}
	
	public void split() {
		this.setShares(this.getSharesOwned()*2);
		this.setPrice(this.getPrice()/2);
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getSharesOwned() {
		return this.sharesOwned;
	}
	
	private void setSymbol(String symbol) {
		if(symbol.length()>=1 &&symbol.length()<=5) 
			this.symbol = symbol;
	}
	
	public void setPrice(double price) {
		if(price>0)
			this.price = price;
	}
	
	private void setShares(int shares) {
		if(shares>0)
			this.sharesOwned = shares;
		else
			shares = 0;
	}
	
	private void setDatePurchased() {
		this.datePurchased = new Date();
	}
	
	private void setDateSold() {
		this.dateSold = new Date();
	}
	
	public String getName() {
		return this.name;
	}
	
	private void setPurchasePrice(double price) {
		this.purchasePrice = price;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public Stock(String symbol, String name, double price, int shares){
		this.setSymbol(symbol);
		this.setName(name);
		this.setPrice(price);
		this.setPurchasePrice(price);
		this.setShares(shares);
		this.setDatePurchased();
		this.cashFromSales = 0;
		this.origShares = shares;
	}
}
