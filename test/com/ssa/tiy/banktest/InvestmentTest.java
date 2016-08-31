package com.ssa.tiy.banktest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ssa.tiy.bank.Investment;

public class InvestmentTest {

	private Investment invest;
	@Before
	public void setUp() throws Exception {
		invest = new Investment();
		invest.deposit(500.00);
		invest.purchaseStock("UA","Under Armour", 40.00, 10);
	}

	@Test
	public void testGetBalance() {
		assertEquals(500.00,invest.getBalance(),0);
	}

	@Test
	public void testPurchaseStock() {
		invest.purchaseStock("FB","Facebook", 95.00, 1);
		assertEquals(5.00,invest.getCashBalance(),0);
		assertEquals(495.00,invest.getInvestmentBalance(),0);
	}

	@Test
	public void testGetCashBalance() {
		assertEquals(100.00,invest.getCashBalance(),0);
	}

	@Test
	public void testGetInvestmentBalance() {
		assertEquals(400.00,invest.getInvestmentBalance(),0);
	}
	
	@Test
	public void testUpdateStockPrice() {
		invest.updateStockPrice("UA", 50.00);
		assertEquals(500.00,invest.getInvestmentBalance(),0);
	}
	
	@Test
	public void testSellStock() {
		invest.sellStock("UA", 10);
		assertEquals(0,invest.getInvestmentBalance(),0);
		
		invest.sellStock("UA", 10);
		assertEquals(0,invest.getInvestmentBalance(),0);
	}

}
