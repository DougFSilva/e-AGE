package com.dougfsilva.e_AGE.dominio.exception;

public class ErroDeOperacaoComPessoaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroDeOperacaoComPessoaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroDeOperacaoComPessoaException(String message) {
		super(message);
	}

}
