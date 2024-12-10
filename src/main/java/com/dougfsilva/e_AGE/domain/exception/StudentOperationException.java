package com.dougfsilva.e_AGE.domain.exception;

public class StudentOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StudentOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentOperationException(String message) {
		super(message);
	}

}
