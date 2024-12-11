package com.dougfsilva.e_AGE.domain.exception;

public class ClazzValidatorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ClazzValidatorException(String message) {
		super(message);
	}

	public ClazzValidatorException(String message, Throwable cause) {
		super(message, cause);
	}
}