package com.dougfsilva.e_AGE.domain.exception;

public class SystemConfigurationOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SystemConfigurationOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemConfigurationOperationException(String message) {
		super(message);
	}

}
