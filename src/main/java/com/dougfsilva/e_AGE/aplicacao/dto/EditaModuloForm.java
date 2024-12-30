package com.dougfsilva.e_AGE.aplicacao.dto;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EditaModuloForm(String ID, String codigo, String turmaID, LocalDate dataDeAbertura) {

	public EditaModuloForm(String ID, String codigo, String turmaID, LocalDate dataDeAbertura) {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
		this.ID = ID;
		this.codigo = codigo;
		this.turmaID = turmaID;
		this.dataDeAbertura = dataDeAbertura;
	}
}
