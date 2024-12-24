package com.dougfsilva.e_AGE.dominio.exception;

public class UsuarioNaoAutorizadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoAutorizadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNaoAutorizadoException(String message) {
		super(message);
	}

}
