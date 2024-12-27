package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeMatriculaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeMatriculaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeMatriculaException(String message) {
		super(message);
	}

}
