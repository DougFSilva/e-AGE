package com.dougfsilva.e_AGE.aplicacao.formulario;

import com.dougfsilva.e_AGE.dominio.curso.Modalidade;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaCursoForm(Modalidade modalidade, String areaTecnologicaID, String titulo, String descricao) {

	public CriaCursoForm {
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
	}
}
