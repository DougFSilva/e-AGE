package com.dougfsilva.e_AGE.aplicacao.formulario;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record AssinaOcorrenciaManualmenteForm(String ocorrenciaID, String assinaturaResponsavel, String justificativa) {

	public AssinaOcorrenciaManualmenteForm {
		if (ocorrenciaID == null || ocorrenciaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID da ocorrência não pode ser nulo ou vazio");
		}
		if (assinaturaResponsavel == null || assinaturaResponsavel.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo assinatura do responsável não pode ser nulo ou vazio");
		}
		if (justificativa == null || justificativa.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo justificativa não pode ser nulo ou vazio");
		}
	}
}
