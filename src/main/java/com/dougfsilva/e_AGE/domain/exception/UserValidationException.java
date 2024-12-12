package com.dougfsilva.e_AGE.domain.exception;

public class UserValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserValidationException(String message) {
		super(message);
	}
	
}
