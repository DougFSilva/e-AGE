package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComEvasaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComEvasaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComEvasaoException(String message) {
		super(message);
	}

}
