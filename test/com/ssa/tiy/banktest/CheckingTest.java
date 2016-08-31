package com.ssa.tiy.banktest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ssa.tiy.bank.Checking;

public class CheckingTest {

	private Checking checking;
	
	@Before
	public void setUp() throws Exception {
		checking = new Checking();
	}

	@Test
	public void testWithdrawIntDouble() {
		double testDeposit = 100;
		double testWithdraw = 100;
		
		checking.deposit(testDeposit);
		checking.withdraw(testWithdraw);
		
		assertEquals("Good withdraw",testDeposit-testWithdraw,checking.getBalance(),0);
		
		//Withdraw more the balance
		checking.withdraw(checking.getBalance() + 1000);
		if (checking.getBalance() < 0)
			fail("Allowed to withdraw more than the current balance");
	}

	@Test
	public void testCheckingIntString() {
		checking = new Checking(150,"Description");
		
		assertEquals("Account ID set by constructor",150,checking.getId(),0);
		assertEquals("Description set by constructor","Description",checking.getDescription());
		assertEquals("Balance set by default",0,checking.getBalance(),0);
	}

	@Test
	public void testCheckingString() {
		checking = new Checking("Description");
		
		assertEquals("Description set by constructor","Description",checking.getDescription());
		assertEquals("Balance set by default",0,checking.getBalance(),0);
		if (checking.getId() <= 0) {
			fail("Id not properly set by default");
		}
	}

	@Test
	public void testChecking() {
		checking = new Checking();
		
		assertEquals("Description set by constructor","",checking.getDescription());
		assertEquals("Balance set by default",0,checking.getBalance(),0);
		if (checking.getId() <= 0) {
			fail("Id not properly set by default");
		}
	}
}
