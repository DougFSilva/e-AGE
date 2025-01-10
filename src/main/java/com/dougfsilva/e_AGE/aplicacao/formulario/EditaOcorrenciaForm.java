package com.dougfsilva.e_AGE.aplicacao.formulario;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.TipoOcorrencia;

public record EditaOcorrenciaForm(String ID, String moduloID, TipoOcorrencia tipo, Boolean encaminhada, Boolean restrita, String descricao) {

	public EditaOcorrenciaForm {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
	}

}
