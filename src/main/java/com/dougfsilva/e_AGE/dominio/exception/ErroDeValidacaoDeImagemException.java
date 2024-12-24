package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeImagemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeImagemException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeImagemException(String message) {
		super(message);
	}

}
