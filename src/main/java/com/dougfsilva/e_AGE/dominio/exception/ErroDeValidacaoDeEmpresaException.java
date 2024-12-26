package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDeEmpresaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDeEmpresaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDeEmpresaException(String message) {
		super(message);
	}

}
