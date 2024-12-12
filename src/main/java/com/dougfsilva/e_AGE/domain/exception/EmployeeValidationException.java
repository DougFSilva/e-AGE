package com.dougfsilva.e_AGE.domain.exception;

public class EmployeeValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeValidationException(String message) {
		super(message);
	}

}
