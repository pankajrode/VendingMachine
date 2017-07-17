package org.vending;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestVendingMachine  {
	
	private VendingMachine vendingMachine;
	private ProductInventory productInventory;
	private CashInventory cashInventory;
	
	@Before
	public void setup() {
		productInventory = new ProductInventory();
		cashInventory = new CashInventory();

		for (Coin c : Coin.values()) {
			cashInventory.put(c, 10);
		}

		for (Product p : Product.values()) {
			productInventory.put(p, 10);
		}

		vendingMachine = new VendingMachineImpl(productInventory, cashInventory);
	}

	@Test
	public void testSelectProductAndGetPrice() {
		Product coke = Product.COKE;
		int price = vendingMachine.selectProductAndGetPrice(coke);
		Assert.assertTrue("Product Price expected is 25",price == 25);
	}

	@Test
	public void insertCoin() {
		Coin coin = Coin.FIVE;
		vendingMachine.insertCoin(coin);
		int count = cashInventory.getQuantity(coin);
		Assert.assertEquals(11, count);
		
	}

	@Test
	public void testReceiveProduct() {
		Product product = Product.COKE;
		int price = vendingMachine.selectProductAndGetPrice(product);
		Coin coin1 = Coin.TEN;
		Coin coin2 = Coin.TEN;
		Coin coin3 = Coin.FIVE;
		vendingMachine.insertCoin(coin1);vendingMachine.insertCoin(coin2);vendingMachine.insertCoin(coin3);
		Product receivedProduct = vendingMachine.receiveProduct();
		Assert.assertNotNull(receivedProduct);
		Assert.assertEquals(product.prodcutName(), receivedProduct.prodcutName());
	}

	@Test
	public void receiveRemainingChangeIfAny() {
		Product product = Product.COKE;
		int price = vendingMachine.selectProductAndGetPrice(product);
		Coin coin1 = Coin.TEN;
		Coin coin2 = Coin.TEN;
		Coin coin3 = Coin.TEN;
		vendingMachine.insertCoin(coin1);vendingMachine.insertCoin(coin2);vendingMachine.insertCoin(coin3);
		Product receivedProduct = vendingMachine.receiveProduct();
		Assert.assertNotNull(receivedProduct);
		Assert.assertEquals(product.prodcutName(), receivedProduct.prodcutName());
		
		int beforeRefund = cashInventory.getQuantity(Coin.FIVE);
		
		List<Coin> change = vendingMachine.receiveRemainingChangeIfAny();
		Assert.assertFalse(change.isEmpty());
		Coin coin = change.get(0);
		Assert.assertEquals(Coin.FIVE.denomination(), coin.denomination());
		
		int afterRefund = cashInventory.getQuantity(Coin.FIVE);
		
		Assert.assertEquals(1, beforeRefund - afterRefund);

	}

	public void resetVendingMachine() {
		// TODO Auto-generated method stub
		
	}

}
