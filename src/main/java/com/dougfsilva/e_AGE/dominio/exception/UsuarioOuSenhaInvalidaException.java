package com.dougfsilva.e_AGE.dominio.exception;

public class UsuarioOuSenhaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioOuSenhaInvalidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioOuSenhaInvalidaException(String message) {
		super(message);
	}

}
