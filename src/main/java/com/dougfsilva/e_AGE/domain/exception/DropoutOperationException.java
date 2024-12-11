package com.dougfsilva.e_AGE.domain.exception;

public class DropoutOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DropoutOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DropoutOperationException(String message) {
		super(message);
	}

}
