package com.dougfsilva.e_AGE.domain.exception;

public class EnrollmentValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EnrollmentValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnrollmentValidationException(String message) {
		super(message);
	}
	
}
