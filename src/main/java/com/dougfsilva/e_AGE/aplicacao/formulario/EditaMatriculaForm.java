package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EditaMatriculaForm(String ID, String registro, LocalDate dataDaMatricula) {

	public EditaMatriculaForm {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
	}
}
