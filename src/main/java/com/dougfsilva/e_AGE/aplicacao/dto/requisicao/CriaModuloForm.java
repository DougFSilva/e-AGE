package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaModuloForm(String cursoID, String codigo) {

	public CriaModuloForm(String cursoID, String codigo) {
		if (cursoID == null || cursoID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo Id do curso não pode ser nulo ou vazio");
		}
		if (codigo == null || codigo.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo codigo não pode ser nulo ou vazio");
		}
		this.cursoID = cursoID;
		this.codigo = codigo;
	}
}
