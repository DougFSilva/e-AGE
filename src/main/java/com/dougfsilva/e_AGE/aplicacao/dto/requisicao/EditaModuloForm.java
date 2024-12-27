package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EditaModuloForm(String ID, String cursoID, String codigo) {

	public EditaModuloForm(String ID, String cursoID, String codigo) {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
		this.ID = ID;
		this.cursoID = cursoID;
		this.codigo = codigo;
	}
}
