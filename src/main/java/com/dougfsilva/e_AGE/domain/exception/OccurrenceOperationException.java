package com.dougfsilva.e_AGE.domain.exception;

public class OccurrenceOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OccurrenceOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public OccurrenceOperationException(String message) {
		super(message);
	}

}
