package com.dougfsilva.e_AGE.domain.exception;

public class EnrollmentValidatorException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EnrollmentValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnrollmentValidatorException(String message) {
		super(message);
	}
	
}
