package com.dougfsilva.e_AGE.aplicacao.formulario;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record TrataOcorrenciaForm(String ocorrenciaID, String tratamento) {

	public TrataOcorrenciaForm {
		if (ocorrenciaID == null || ocorrenciaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID da ocorrência não pode ser nulo ou vazio");
		}
		if (tratamento == null || tratamento.isBlank() || tratamento.length() < 10) {
			throw new ErroDeValidacaoDeCamposException("O campo tratamento não pode ser nulo, vazio, ou possuir menos de 10 caractéres");
		}
	}
}
