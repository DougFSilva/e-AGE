package com.dougfsilva.e_AGE.domain.exception;

public class ClazzOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClazzOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClazzOperationException(String message) {
		super(message);
	}

}
