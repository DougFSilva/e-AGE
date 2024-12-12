package com.dougfsilva.e_AGE.domain.exception;

public class EnterpriseValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnterpriseValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnterpriseValidationException(String message) {
		super(message);
	}

}
