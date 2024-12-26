package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import com.dougfsilva.e_AGE.dominio.curso.Modalidade;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaCursoForm(Modalidade modalidade, String areaTecnologicaID, String titulo, String descricao) {

	public CriaCursoForm(Modalidade modalidade, String areaTecnologicaID, String titulo, String descricao) {
		if (modalidade == null) {
			throw new ErroDeValidacaoDeCamposException("O campo modalidade não pode ser nulo");
		}
		if (areaTecnologicaID == null || areaTecnologicaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID da área tecnológica não pode ser nulo ou vazio");
		}
		if (titulo == null || titulo.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo título não pode ser nulo ou vazio");
		}
		if (descricao == null || descricao.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo descrição não pode ser nulo ou vazio");
		}
		this.modalidade = modalidade;
		this.areaTecnologicaID = areaTecnologicaID;
		this.titulo = titulo;
		this.descricao = descricao;
	}
}
