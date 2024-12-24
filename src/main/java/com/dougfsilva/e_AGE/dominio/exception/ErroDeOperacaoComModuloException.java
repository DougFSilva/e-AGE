package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComModuloException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComModuloException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComModuloException(String message) {
		super(message);
	}

}
