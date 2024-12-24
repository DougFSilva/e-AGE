package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeUsuarioException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeUsuarioException(String message) {
		super(message);
	}
	
}
