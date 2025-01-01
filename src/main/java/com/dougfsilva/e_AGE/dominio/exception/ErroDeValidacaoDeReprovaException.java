package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeReprovaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeReprovaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeReprovaException(String message) {
		super(message);
	}

}
