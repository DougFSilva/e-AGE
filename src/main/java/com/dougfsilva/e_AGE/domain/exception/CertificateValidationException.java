package com.dougfsilva.e_AGE.domain.exception;

public class CertificateValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CertificateValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CertificateValidationException(String message) {
		super(message);
	}
	
}
