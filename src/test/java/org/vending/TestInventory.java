package org.vending;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestInventory {
	
	private static Inventory<Product> productInventory;
	
	@Before
	public void setUp() {
		productInventory = new Inventory<>();
		productInventory.put(Product.COKE, 10);
		productInventory.put(Product.PEPSI, 10);
		productInventory.put(Product.SODA, 10);
	}
	
	@Test
	public void testAddItem() {
		productInventory.add(Product.COKE);
		productInventory.add(Product.PEPSI);
		productInventory.add(Product.SODA);
		
		Assert.assertEquals(11, productInventory.getQuantity(Product.COKE));
		Assert.assertEquals(11, productInventory.getQuantity(Product.PEPSI));
		Assert.assertEquals(11, productInventory.getQuantity(Product.SODA));
	}
	
	@Test
	public void testDeductItem() {
		productInventory.deduct(Product.COKE);
		productInventory.deduct(Product.PEPSI);
		productInventory.deduct(Product.SODA);
		
		Assert.assertEquals(9, productInventory.getQuantity(Product.COKE));
		Assert.assertEquals(9, productInventory.getQuantity(Product.PEPSI));
		Assert.assertEquals(9, productInventory.getQuantity(Product.SODA));
	}
	
	@Test
	public void testGetQuantity() {
		int quantity = productInventory.getQuantity(Product.COKE);
		Assert.assertEquals(10, quantity);
	}
	
	@Test
	public void testHasItem() {
		boolean hasItem = productInventory.hasItem(Product.COKE);
		Assert.assertTrue(hasItem);
	}
	
	@Test
	public void testReset() {
		productInventory.reset();
		int quantity = productInventory.getQuantity(Product.COKE);
		Assert.assertEquals(0, quantity);
	}

}
