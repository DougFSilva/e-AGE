package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeCamposException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeCamposException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeCamposException(String message) {
		super(message);
	}

}
