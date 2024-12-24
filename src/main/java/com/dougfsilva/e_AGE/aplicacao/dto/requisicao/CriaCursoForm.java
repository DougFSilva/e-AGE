package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import com.dougfsilva.e_AGE.dominio.curso.Modalidade;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCursoException;

public record CriaCursoForm(
		
		Modalidade modalidade,
		String areaTecnologicaID,
		String titulo,
		String descricao) {

	public CriaCursoForm(Modalidade modalidade, String areaTecnologicaID, String titulo, String descricao) {
		if (modalidade == null) {
			throw new ErroDeValidacaoDeCursoException("O campo modalidade não pode ser nulo");
		}
		if (areaTecnologicaID == null || areaTecnologicaID.isBlank()) {
			throw new ErroDeValidacaoDeCursoException("O campo areaTecnologicaID não pode ser nulo ou vazio");
		}
		if (titulo == null || titulo.isBlank()) {
			throw new ErroDeValidacaoDeCursoException("O campo titulo não pode ser nulo ou vazio");
		}
		if (descricao == null || descricao.isBlank()) {
			throw new ErroDeValidacaoDeCursoException("O campo descricao não pode ser nulo ou vazio");
		}
		this.modalidade = modalidade;
		this.areaTecnologicaID = areaTecnologicaID;
		this.titulo = titulo;
		this.descricao = descricao;
	}
}
