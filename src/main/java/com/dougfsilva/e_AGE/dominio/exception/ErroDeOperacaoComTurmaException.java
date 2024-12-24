package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComTurmaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComTurmaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComTurmaException(String message) {
		super(message);
	}

}
