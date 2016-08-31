package com.ssa.tiy.banktest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ssa.tiy.bank.Savings;

public class SavingsTest {

	private Savings savings;
	
	@Before
	public void setUp() throws Exception {
		savings = new Savings();
	}

	@Test
	public void testPrint() {
		if(savings.print() == null || savings.print().length() == 0)
			fail("Print returns null or empty String");
	}

	@Test
	public void testSavingsIntString() {
		savings = new Savings(150,"Description");
		
		assertEquals("Account ID set by constructor",150,savings.getId(),0);
		assertEquals("Description set by constructor","Description",savings.getDescription());
		assertEquals("Balance set by default",0,savings.getBalance(),0);
	}

	@Test
	public void testSavingsString() {
		savings = new Savings("Description");
		
		assertEquals("Description set by constructor","Description",savings.getDescription());
		assertEquals("Balance set by default",0,savings.getBalance(),0);
		if (savings.getId() <= 0) {
			fail("Id not properly set by default");
		}
	}

	@Test
	public void testSavings() {
		assertEquals("Description set by default","",savings.getDescription());
		assertEquals("Balance set by default",0,savings.getBalance(),0);
		if (savings.getId() <= 0) {
			fail("Id not properly set by default");
		}
	}

	@Test
	public void testGetInterestRate() {
		double testInterestRate = .025;
		
		savings.setInterestRate(testInterestRate);
		assertEquals("Get Inteterest",testInterestRate,savings.getInterestRate(),0);
	}

	@Test
	public void testSetInterestRate() {
		//Ensure negative interest rates cannot be set
		double badInterestRate = -.05;
		savings.setInterestRate(badInterestRate);
		if (savings.getInterestRate() == badInterestRate)
			fail("Allows negative interest rate");
		
		double testInterestRate = .025;
		
		savings.setInterestRate(testInterestRate);
		assertEquals("Set Inteterest",testInterestRate,savings.getInterestRate(),0);
	}

	@Test
	public void testSetMinBalance() {
		double testMinBalance = 500;
		
		savings.setMinBalance(testMinBalance);
		assertEquals("Set minimum balance",testMinBalance,savings.getMinBalance(),0);
		
		//Ensure minimum balance cannot be set negative
		savings.setMinBalance(-testMinBalance);
		assertEquals("Negative test",testMinBalance,savings.getMinBalance(),0);
	}

	@Test
	public void testGetMinBalance() {
	double testMinBalance = 500;
		
		savings.setMinBalance(testMinBalance);
		assertEquals("Get minimum balance",testMinBalance,savings.getMinBalance(),0);
	}

	@Test
	public void testGetInterestAccrued() {
		double testDeposit = 100;
		int testMonths = 12;
		double testInterestRate = .25;
		
		savings.setInterestRate(testInterestRate);
		savings.deposit(testDeposit);
		savings.calcDepositInterest(testMonths);
		
		assertEquals("Interest accrued",testDeposit*((testInterestRate*testMonths)/12),
				savings.getInterestAccrued(),0.005);
	}

	@Test
	public void testCalcDepositInterest() {
		double testDeposit = 100;
		int testMonths = 12;
		double testInterestRate = .25;
		
		savings.setInterestRate(testInterestRate);
		savings.deposit(testDeposit);
		
		assertEquals("Interest Deposit",testDeposit*((testInterestRate*testMonths)/12),
				savings.calcDepositInterest(testMonths),0.005);
	}
}
