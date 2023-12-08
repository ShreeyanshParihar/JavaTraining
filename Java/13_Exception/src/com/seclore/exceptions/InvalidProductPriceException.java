package com.seclore.exceptions;

public class InvalidProductPriceException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidProductPriceException() {
		System.out.println("InvalidProductPriceException constructor called.");
	}
	
	@Override
	public String getMessage() {
		return "Invalid price, it must be > 0";
	}
}
