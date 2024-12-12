package com.dougfsilva.e_AGE.domain.exception;

public class PersonValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersonValidationException(String message) {
		super(message);
	}

}
