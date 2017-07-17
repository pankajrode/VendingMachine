package org.vending;

import java.util.List;

public interface VendingMachine {
	int selectProductAndGetPrice(Product product);
	void insertCoin(Coin coin);
	Product receiveProduct();
	List<Coin> receiveRemainingChangeIfAny();
	void resetVendingMachine();
}
