package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDePessoaException;
import com.dougfsilva.e_AGE.dominio.pessoa.PessoaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaPessoa {

	private final PessoaRepository repository;
	
	public void validaUnicoRG(String RG) {
		if(repository.existePeloRG(RG)) {
			throw new ErroDeValidacaoDePessoaException(String.format("RG de pessoa %s já existe na base de dados", RG));
		}
	}
}
