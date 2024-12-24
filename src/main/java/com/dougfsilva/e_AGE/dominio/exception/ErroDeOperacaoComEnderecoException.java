package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComEnderecoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComEnderecoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComEnderecoException(String message) {
		super(message);
	}

}
