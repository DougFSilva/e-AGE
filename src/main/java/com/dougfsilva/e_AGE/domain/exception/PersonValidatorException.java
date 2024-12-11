package com.dougfsilva.e_AGE.domain.exception;

public class PersonValidatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersonValidatorException(String message) {
		super(message);
	}

}
