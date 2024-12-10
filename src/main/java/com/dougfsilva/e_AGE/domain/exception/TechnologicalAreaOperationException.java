package com.dougfsilva.e_AGE.domain.exception;

public class TechnologicalAreaOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TechnologicalAreaOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnologicalAreaOperationException(String message) {
		super(message);
	}

}
