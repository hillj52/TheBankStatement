package com.ssa.tiy.bank;

import java.util.HashMap;

public class Checking extends Account{
	
	private HashMap <Integer,Double> checkLog;
	private int lastCheckNum;
	
	public String print() {
		return this.toString();
	}
	
	public String toString() {
		return "C " + super.toString() + " " + String.format("%-4s", this.getLastCheckNum());
	}
	
	public void setLastCheckNum(int lastCheckNum) {
		this.lastCheckNum = lastCheckNum;
	}
	
	public double withdraw(double amount) {
		checkLog.put(lastCheckNum++, amount);
		return super.withdraw(amount);
	}
	
	public int getLastCheckNum() {
		return this.lastCheckNum;
	}
	
	public Checking(int id, String description) {
		super(id,description);
		this.checkLog = new HashMap<Integer,Double>();
		this.lastCheckNum = 100;
	}
	
	public Checking(String description) {
		this(idGenner++,description);
	}
	
	public Checking() {
		this (idGenner++,"");
	}
}
