package com.dougfsilva.e_AGE.domain.exception;

public class EnterpriseValidatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnterpriseValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnterpriseValidatorException(String message) {
		super(message);
	}

}
