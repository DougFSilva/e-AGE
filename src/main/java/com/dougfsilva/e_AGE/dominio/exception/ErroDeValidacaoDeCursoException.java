package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeCursoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeCursoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeCursoException(String message) {
		super(message);
	}

}
