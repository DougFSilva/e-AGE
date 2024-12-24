package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComEmpresaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComEmpresaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComEmpresaException(String message) {
		super(message);
	}

}
