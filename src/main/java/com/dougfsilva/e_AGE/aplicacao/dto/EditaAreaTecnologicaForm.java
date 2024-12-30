package com.dougfsilva.e_AGE.aplicacao.dto;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EditaAreaTecnologicaForm(String ID, String titulo, String descricao) {

	public EditaAreaTecnologicaForm(String ID, String titulo, String descricao) {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
		this.ID = ID;
		this.titulo = titulo;
		this.descricao = descricao;
	}
}
