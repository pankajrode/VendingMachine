package org.vending;

import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {
	Map<T,Integer> inventory = new HashMap<>();
	
	public void add(T item) {
		inventory.put(item, getQuantity(item) + 1);
	}
	
	public void deduct(T item) {
		inventory.put(item, getQuantity(item) - 1);
	}
	
	public int getQuantity(T item) {
		return inventory.get(item) == null ? 0 : inventory.get(item) ;
	}
	
	public boolean hasItem(T item) {
		return getQuantity(item) > 0;
	}
	
	public void put(T item, Integer count) {
		inventory.put(item, count);
	}
	
	public void reset() {
		inventory.clear();
	}
}