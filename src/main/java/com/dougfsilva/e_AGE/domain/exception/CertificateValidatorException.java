package com.dougfsilva.e_AGE.domain.exception;

public class CertificateValidatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CertificateValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public CertificateValidatorException(String message) {
		super(message);
	}
	
}
