package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeCertificadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeCertificadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeCertificadoException(String message) {
		super(message);
	}

}
