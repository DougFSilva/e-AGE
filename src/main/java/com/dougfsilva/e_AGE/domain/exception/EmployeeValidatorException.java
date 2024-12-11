package com.dougfsilva.e_AGE.domain.exception;

public class EmployeeValidatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeValidatorException(String message) {
		super(message);
	}

}
