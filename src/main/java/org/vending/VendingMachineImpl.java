package org.vending;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vending.exceptions.ChangeNotAvailableException;
import org.vending.exceptions.NotFullPaidException;
import org.vending.exceptions.ProductNotAvailableException;

@Component
public class VendingMachineImpl implements VendingMachine {
	
	@Autowired
	private ProductInventory productInventory;	
	@Autowired
	private CashInventory cashInventory;
	
	private Product currentProduct;
	private int currentBalance;
	private int totalSales;
	
	public VendingMachineImpl(ProductInventory productInv, CashInventory cashInv) {
		this.productInventory = productInv;
		this.cashInventory = cashInv;
	}

	public int selectProductAndGetPrice(Product product) {
		if(productInventory.hasItem(product)) {
			this.currentProduct = product;
		   return product.price();
		} else {
			throw new ProductNotAvailableException("Product " + product.prodcutName() + " : Not availabe");
		}
	}

	public void insertCoin(Coin coin) {
		this.currentBalance = this.currentBalance + coin.denomination();
		cashInventory.add(coin);
		
	}

	public Product receiveProduct() {
		if (currentBalance >= currentProduct.price()) {
			if (isChangeAvailable()) {
				totalSales = totalSales + currentProduct.price();
				return currentProduct;
			}
			
			throw new ChangeNotAvailableException("Sorry Not Sufficient chnage avialble in inventory....");

		}

		int remianing = currentProduct.price() - currentBalance;
		throw new NotFullPaidException(" Please pay remaining balance " + remianing);

	}

	public List<Coin> receiveRemainingChangeIfAny() {
		List<Coin> change = getChange(currentBalance - currentProduct.price());
		updateInventory(change);
		currentProduct = null;
		currentBalance = 0;
		return change;
	}

	public void resetVendingMachine() {
		cashInventory.reset();
		productInventory.reset();
		totalSales = 0;
		currentBalance = 0;
		currentProduct = null;
	}
	
	public int getTotalSales() {
		return this.totalSales;
	}
	
	private void updateInventory(List<Coin> change) {
		for(Coin coin: change) {
			cashInventory.deduct(coin);
		}
	}
	
	private boolean isChangeAvailable() {
		boolean changeAvailable = Boolean.TRUE;
		int returnChange = currentProduct.price() - currentBalance;
		if(returnChange > 0) {
			try {
				getChange(returnChange);
			} catch (ChangeNotAvailableException e) {
				changeAvailable = Boolean.FALSE;
			}
		}
		
		return changeAvailable;
	}
	
	private List<Coin> getChange(int amount) throws ChangeNotAvailableException {
		long balance = amount;
		List<Coin> change = new ArrayList<>();
		while(balance > 0) {
			if(balance >= Coin.TEN.denomination() && cashInventory.hasItem(Coin.TEN)) {
				balance = balance - Coin.TEN.denomination();
				change.add(Coin.TEN);
				continue;
			} else if(balance >= Coin.FIVE.denomination() && cashInventory.hasItem(Coin.FIVE)) {
				balance = balance - Coin.FIVE.denomination();
				change.add(Coin.FIVE);
				continue;
			} else if(balance >= Coin.TWO.denomination() && cashInventory.hasItem(Coin.TWO)) {
				balance = balance - Coin.TWO.denomination();
				change.add(Coin.TWO);
				continue;
			} else if(balance >= Coin.ONE.denomination() && cashInventory.hasItem(Coin.ONE)) {
				balance = balance - Coin.ONE.denomination();
				change.add(Coin.ONE);
				continue;
			} else {
				throw new ChangeNotAvailableException("Sufficient chnage not avilable for "+balance);
			}
		}
		
		return change;
	}

}
