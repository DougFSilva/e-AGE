package com.dougfsilva.e_AGE.domain.exception;

public class OccurrenceSignatureOperationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public OccurrenceSignatureOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public OccurrenceSignatureOperationException(String message) {
		super(message);
	}
}
