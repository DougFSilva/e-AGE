package com.dougfsilva.e_AGE.domain.exception;

public class UnauthorizedUserException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UnauthorizedUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedUserException(String message) {
		super(message);
	}
	
}
