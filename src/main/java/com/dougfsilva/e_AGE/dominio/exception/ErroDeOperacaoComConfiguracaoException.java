package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComConfiguracaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComConfiguracaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComConfiguracaoException(String message) {
		super(message);
	}

}
