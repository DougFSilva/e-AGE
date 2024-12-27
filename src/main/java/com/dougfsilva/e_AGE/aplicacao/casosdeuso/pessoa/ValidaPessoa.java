package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDePessoaException;
import com.dougfsilva.e_AGE.dominio.pessoa.PessoaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaPessoa {

	private final PessoaRepository repository;
	
	public void validarUnicoCPF(String CPF) {
		if(repository.existePeloCPF(CPF)) {
			throw new ErroDeValidacaoDePessoaException(String.format("CPF de pessoa %s já existe na base de dados", CPF));
		}
	}
	
}
