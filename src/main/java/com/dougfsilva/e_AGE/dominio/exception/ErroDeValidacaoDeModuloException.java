package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeModuloException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeModuloException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeModuloException(String message) {
		super(message);
	}

}
