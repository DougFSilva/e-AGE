package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComImagemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComImagemException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComImagemException(String message) {
		super(message);
	}

}
