package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeUsuarioException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeUsuarioException(String message) {
		super(message);
	}

}
