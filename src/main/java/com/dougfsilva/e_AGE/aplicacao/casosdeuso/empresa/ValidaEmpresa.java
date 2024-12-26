package com.dougfsilva.e_AGE.aplicacao.casosdeuso.empresa;

import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEmpresaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaEmpresa {

	private final EmpresaRepository repository;
	
	public void validarUnicoCNPJ(String CNPJ) {
		if(repository.existePeloCNPJ(CNPJ)) {
			throw new ErroDeValidacaoDeEmpresaException(String.format("CNPJ de empresa %s já existe na base de dados", CNPJ));
		}
	}
	
	public void validarUnicoNome(String nome) {
		if(repository.existePeloNome(nome)) {
			throw new ErroDeValidacaoDeEmpresaException(String.format("Nome de empresa %s já existe na base de dados", nome));
		}
	}
}
