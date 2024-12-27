package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import com.dougfsilva.e_AGE.dominio.curso.Modalidade;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EditaCursoForm(String ID, Modalidade modalidade, String areaTecnologicaID, String titulo, String descricao) {

	public EditaCursoForm(String ID, Modalidade modalidade, String areaTecnologicaID, String titulo, String descricao) {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
		this.ID = ID;
		this.modalidade = modalidade;
		this.areaTecnologicaID = areaTecnologicaID;
		this.titulo = titulo;
		this.descricao = descricao;
	}
}
