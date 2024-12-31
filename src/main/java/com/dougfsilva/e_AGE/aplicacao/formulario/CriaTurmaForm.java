package com.dougfsilva.e_AGE.aplicacao.formulario;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaTurmaForm(String codigo, String cursoID) {

	public CriaTurmaForm {
		if (codigo == null || codigo.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo codigo não pode ser nulo ou vazio");
		}
		if (cursoID == null || cursoID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo Id do curso não pode ser nulo ou vazio");
		}
	}
}
