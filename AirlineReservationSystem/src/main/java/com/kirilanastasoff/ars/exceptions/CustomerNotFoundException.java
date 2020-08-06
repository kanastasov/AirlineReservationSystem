package com.kirilanastasoff.ars.exceptions;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String msg) {
		super("Customer not found " + msg);
	}

}
