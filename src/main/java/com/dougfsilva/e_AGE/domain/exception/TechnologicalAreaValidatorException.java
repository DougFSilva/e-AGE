package com.dougfsilva.e_AGE.domain.exception;

public class TechnologicalAreaValidatorException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TechnologicalAreaValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnologicalAreaValidatorException(String message) {
		super(message);
	}

}
