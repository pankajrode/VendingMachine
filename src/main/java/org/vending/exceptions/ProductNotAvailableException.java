package org.vending.exceptions;

public class ProductNotAvailableException extends RuntimeException {

	private String message;
	
	public ProductNotAvailableException(String msg) {
		this.message = msg;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
