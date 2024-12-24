package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComCertificadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComCertificadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComCertificadoException(String message) {
		super(message);
	}

}
