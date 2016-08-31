package com.ssa.tiy.banktest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ssa.tiy.bank.Stock;

public class StockTest {

	private Stock stock;
	@Before
	public void setUp() throws Exception {
		stock = new Stock("UA","Under Armour",40.00,100);
	}

	@Test
	public void testGetValue() {
		assertEquals(4000.00,stock.getValue(),0);
	}

	@Test
	public void testHasSold() {
		assertFalse(stock.hasSold());
		stock.sell(40);
		assertTrue(stock.hasSold());
	}

	@Test
	public void testSell() {
		assertEquals(1000.00,stock.sell(25),0);
		assertEquals(3000.00,stock.getValue(),0);
	}

	@Test
	public void testSplit() {
		stock.split();
		assertEquals(200,stock.getSharesOwned());
		assertEquals(20.00,stock.getPrice(),0);
	}

	public void testGetSymbol() {
		assertEquals("UA",stock.getSymbol());
	}

	@Test
	public void testGetPrice() {
		assertEquals(40.00,stock.getPrice(),0);
	}

	@Test
	public void testGetSharesOwned() {
		assertEquals(100,stock.getSharesOwned());
	}

	@Test
	public void testSetPrice() {
		stock.setPrice(80.00);
		assertEquals(80.00,stock.getPrice(),0);
	}

}
