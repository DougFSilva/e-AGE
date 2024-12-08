package com.dougfsilva.e_AGE.domain.exception;

public class EmployeeOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeOperationException(String message) {
		super(message);
	}

}
