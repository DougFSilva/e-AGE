package com.dougfsilva.e_AGE.domain.exception;

public class UserValidatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserValidatorException(String message) {
		super(message);
	}
	
}
