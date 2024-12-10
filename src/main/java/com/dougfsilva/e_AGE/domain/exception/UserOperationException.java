package com.dougfsilva.e_AGE.domain.exception;

public class UserOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserOperationException(String message) {
		super(message);
	}

}
