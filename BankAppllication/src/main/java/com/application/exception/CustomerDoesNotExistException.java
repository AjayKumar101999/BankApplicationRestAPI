package com.application.exception;

public class CustomerDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerDoesNotExistException(String message) {
		super(message);
	}
}
