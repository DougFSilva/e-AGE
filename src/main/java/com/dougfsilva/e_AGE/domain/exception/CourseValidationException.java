package com.dougfsilva.e_AGE.domain.exception;

public class CourseValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CourseValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CourseValidationException(String message) {
		super(message);
	}
	
}
