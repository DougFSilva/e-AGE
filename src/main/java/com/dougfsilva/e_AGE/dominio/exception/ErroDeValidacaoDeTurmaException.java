package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeTurmaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeTurmaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeTurmaException(String message) {
		super(message);
	}

}
