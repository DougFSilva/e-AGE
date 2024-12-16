package com.dougfsilva.e_AGE.domain.exception;

public class OccurrenceValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OccurrenceValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public OccurrenceValidationException(String message) {
		super(message);
	}
	
}
