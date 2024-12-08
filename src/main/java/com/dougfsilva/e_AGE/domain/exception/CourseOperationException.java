package com.dougfsilva.e_AGE.domain.exception;

public class CourseOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CourseOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CourseOperationException(String message) {
		super(message);
	}

}
