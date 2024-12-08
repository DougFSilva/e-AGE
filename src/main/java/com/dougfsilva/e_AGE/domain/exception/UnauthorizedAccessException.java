package com.dougfsilva.e_AGE.domain.exception;

public class UnauthorizedAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedAccessException(String message) {
		super(message);
	}

}
