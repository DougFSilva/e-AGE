package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComAlunoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComAlunoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComAlunoException(String message) {
		super(message);
	}

}
