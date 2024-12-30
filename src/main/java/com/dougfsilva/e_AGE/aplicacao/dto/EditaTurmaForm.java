package com.dougfsilva.e_AGE.aplicacao.dto;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EditaTurmaForm(String ID, String codigo, String cursoID, LocalDate dataDeAbertura) {

	public EditaTurmaForm(String ID, String codigo, String cursoID, LocalDate dataDeAbertura) {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
		this.ID = ID;
		this.codigo = codigo;
		this.cursoID = cursoID;
		this.dataDeAbertura = dataDeAbertura;
	}
}
