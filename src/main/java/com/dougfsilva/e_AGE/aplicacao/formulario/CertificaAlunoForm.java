package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CertificaAlunoForm(String matriculaID, LocalDate data) {

	public CertificaAlunoForm {
		if (matriculaID == null || matriculaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID da matrícula não pode ser nulo ou vazio");
		}
		if (data == null) {
			throw new ErroDeValidacaoDeCamposException("O campo data da certificação não pode ser nulo");
		}
	}
}
