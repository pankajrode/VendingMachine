package org.vending;

public enum Coin {
   ONE(1), TWO(2),FIVE(5),TEN(10);
	
	private final int demonination;
	
	Coin(int denomination) {
		this.demonination = denomination;
	}
	
	public int denomination() { return demonination; }
   
}
