package com.dougfsilva.e_AGE.aplicacao.formulario;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaAreaTecnologicaForm(String titulo, String descricao) {

	public CriaAreaTecnologicaForm {
		if (titulo == null || titulo.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo titulo não pode ser nulo ou vazio");
		}
		if (descricao == null || descricao.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo descrição não pode ser nulo ou vazio");
		}
	}
}
