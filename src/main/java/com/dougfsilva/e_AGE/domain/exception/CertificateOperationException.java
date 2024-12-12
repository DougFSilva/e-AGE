package com.dougfsilva.e_AGE.domain.exception;

public class CertificateOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CertificateOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CertificateOperationException(String message) {
		super(message);
	}
	
}
