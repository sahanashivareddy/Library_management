package org.paycorp.librarymanagmentsystem.exception;

public class DataNotFoundException extends RuntimeException {
	String message;

	public DataNotFoundException(String message) {
		super();
		this.message = message;
	}

	public DataNotFoundException() {

	}
}
