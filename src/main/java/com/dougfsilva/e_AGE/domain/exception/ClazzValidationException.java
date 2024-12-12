package com.dougfsilva.e_AGE.domain.exception;

public class ClazzValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ClazzValidationException(String message) {
		super(message);
	}

	public ClazzValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}