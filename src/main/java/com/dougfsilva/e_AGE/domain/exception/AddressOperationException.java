package com.dougfsilva.e_AGE.domain.exception;

public class AddressOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AddressOperationException(String message) {
		super(message);
	}

	public AddressOperationException(String message, Throwable cause) {
		super(message, cause);
	}

}
