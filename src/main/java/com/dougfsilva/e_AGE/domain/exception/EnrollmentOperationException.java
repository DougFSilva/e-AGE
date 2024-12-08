package com.dougfsilva.e_AGE.domain.exception;

public class EnrollmentOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnrollmentOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnrollmentOperationException(String message) {
		super(message);
	}

}
