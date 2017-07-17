package org.vending;

public enum Product {
	COKE("Coke",25),PEPSI("Pepsi",30),SODA("Soda",10);
	
	private final String name;
	private final int price;
	
	Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String prodcutName() { return name; }
	
	public int price() { return price; }
}
