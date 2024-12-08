package com.dougfsilva.e_AGE.domain.exception;

public class PersonOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersonOperationException(String message) {
		super(message);
	}

}
