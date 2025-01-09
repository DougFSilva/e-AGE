package com.dougfsilva.e_AGE.aplicacao.formulario;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record AssinaOcorrenciaPeloAlunoForm(String ocorrenciaID, String assinatura) {

	public AssinaOcorrenciaPeloAlunoForm {
		if (ocorrenciaID == null || ocorrenciaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID da ocorrência não pode ser nulo ou vazio");
		}
		if (assinatura == null || assinatura.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo assinatura não pode ser nulo ou vazio");
		}
	}

}
