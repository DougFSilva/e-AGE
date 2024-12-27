package com.dougfsilva.e_AGE.dominio.exception;

public class SenhaDeUsuarioInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SenhaDeUsuarioInvalidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public SenhaDeUsuarioInvalidaException(String message) {
		super(message);
	}

}
