package org.vending.exceptions;

public class ChangeNotAvailableException extends RuntimeException {

	private String message;
	
	public ChangeNotAvailableException(String msg) {
		this.message = msg;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
