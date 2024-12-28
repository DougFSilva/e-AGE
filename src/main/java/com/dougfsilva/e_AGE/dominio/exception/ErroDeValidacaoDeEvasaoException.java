package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeEvasaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeEvasaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeEvasaoException(String message) {
		super(message);
	}

}
