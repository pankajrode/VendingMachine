package org.vending.exceptions;

public class NotFullPaidException extends RuntimeException {

	private String message;
	
	public NotFullPaidException(String msg) {
		this.message = msg;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
