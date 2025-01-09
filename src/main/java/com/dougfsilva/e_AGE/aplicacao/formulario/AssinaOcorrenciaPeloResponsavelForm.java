package com.dougfsilva.e_AGE.aplicacao.formulario;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record AssinaOcorrenciaPeloResponsavelForm(String ocorrenciaID, String PIN) {

	public AssinaOcorrenciaPeloResponsavelForm {
		if (ocorrenciaID == null || ocorrenciaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID da ocorrência não pode ser nulo ou vazio");
		}
		if (PIN == null || PIN.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo PIN não pode ser nulo ou vazio");
		}
	}
}
