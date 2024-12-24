package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComEmailException(String message) {
		super(message);
	}

}
