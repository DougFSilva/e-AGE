package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeOcorrenciaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeOcorrenciaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeOcorrenciaException(String message) {
		super(message);
	}

}
