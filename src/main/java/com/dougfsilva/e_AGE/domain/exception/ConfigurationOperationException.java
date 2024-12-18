package com.dougfsilva.e_AGE.domain.exception;

public class ConfigurationOperationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ConfigurationOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigurationOperationException(String message) {
		super(message);
	}
}
