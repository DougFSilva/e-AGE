package com.dougfsilva.e_AGE.aplicacao.dto;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EditaEmpresaForm(String ID, String CNPJ, String nome, EditaEnderecoForm endereco) {

	public EditaEmpresaForm(String ID, String CNPJ, String nome, EditaEnderecoForm endereco) {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
		this.ID = ID;
		this.CNPJ = CNPJ;
		this.nome = nome;
		this.endereco = endereco;
	}
}
