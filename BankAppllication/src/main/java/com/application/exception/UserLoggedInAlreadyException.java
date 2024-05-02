package com.application.exception;

public class UserLoggedInAlreadyException extends RuntimeException{

	public UserLoggedInAlreadyException(String message) {
		super(message);
	}

	
}
