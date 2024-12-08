package com.dougfsilva.e_AGE.domain.exception;

public class EnterpriseOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnterpriseOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnterpriseOperationException(String message) {
		super(message);
	}

}
