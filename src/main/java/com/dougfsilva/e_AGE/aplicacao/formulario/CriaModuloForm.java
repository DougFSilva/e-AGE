package com.dougfsilva.e_AGE.aplicacao.formulario;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaModuloForm(String codigo, String turmaID) {

	public CriaModuloForm{
		if (codigo == null || codigo.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo codigo não pode ser nulo ou vazio");
		}
		if (turmaID == null || turmaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo Id da turma não pode ser nulo ou vazio");
		}
	}
}
