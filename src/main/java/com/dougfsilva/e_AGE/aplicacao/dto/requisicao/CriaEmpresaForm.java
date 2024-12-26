package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaEmpresaForm(String CNPJ, String nome, CriaEnderecoForm endereco) {

	public CriaEmpresaForm(String CNPJ, String nome, CriaEnderecoForm endereco) {
		if (CNPJ == null || CNPJ.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo CNPJ não pode ser nulo ou vazio");
		}
		if (nome == null || nome.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo nome não pode ser nulo ou vazio");
		}
		if (endereco == null) {
			throw new ErroDeValidacaoDeCamposException("O campo endereço não pode ser nulo");
		}
		this.CNPJ = CNPJ;
		this.nome = nome;
		this.endereco = endereco;
	}
}
