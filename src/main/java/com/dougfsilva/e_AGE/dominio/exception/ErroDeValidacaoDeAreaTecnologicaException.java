package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeAreaTecnologicaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeAreaTecnologicaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeAreaTecnologicaException(String message) {
		super(message);
	}

}
