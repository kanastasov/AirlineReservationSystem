package com.kirilanastasoff.ars.exceptions;

public class CustomerException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public CustomerException(String errorMessage) {
		super("Customer not found " + errorMessage);
		this.errorMessage = errorMessage;
	}

	public CustomerException() {
		super();
	}

}
