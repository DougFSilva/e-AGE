package com.dougfsilva.e_AGE.domain.utilities.exception;

public class InvalidUserOrPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidUserOrPasswordException(String message) {
		super(message);
	}
	
	public InvalidUserOrPasswordException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
