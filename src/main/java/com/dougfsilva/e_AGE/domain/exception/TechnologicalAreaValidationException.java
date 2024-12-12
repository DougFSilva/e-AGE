package com.dougfsilva.e_AGE.domain.exception;

public class TechnologicalAreaValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TechnologicalAreaValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnologicalAreaValidationException(String message) {
		super(message);
	}

}
