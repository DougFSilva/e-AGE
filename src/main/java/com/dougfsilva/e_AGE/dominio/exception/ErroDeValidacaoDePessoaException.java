package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeValidacaoDePessoaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeValidacaoDePessoaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeValidacaoDePessoaException(String message) {
		super(message);
	}

}
