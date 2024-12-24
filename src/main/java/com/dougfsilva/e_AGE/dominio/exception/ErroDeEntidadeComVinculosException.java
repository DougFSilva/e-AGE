package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeEntidadeComVinculosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeEntidadeComVinculosException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeEntidadeComVinculosException(String message) {
		super(message);
	}

}
