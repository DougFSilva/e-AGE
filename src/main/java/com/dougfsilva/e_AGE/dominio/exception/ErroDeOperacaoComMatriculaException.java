package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComMatriculaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComMatriculaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComMatriculaException(String message) {
		super(message);
	}

}
