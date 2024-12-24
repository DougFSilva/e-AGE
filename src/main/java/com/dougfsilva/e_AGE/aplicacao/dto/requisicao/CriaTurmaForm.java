package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaTurmaForm(String codigo, String cursoID, LocalDate dataDeAbertura) {

	public CriaTurmaForm(String codigo, String cursoID, LocalDate dataDeAbertura) {
		if (codigo == null || codigo.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo codigo não pode ser nulo ou vazio");
		}
		if (cursoID == null || cursoID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo cursoID não pode ser nulo ou vazio");
		}
		if (dataDeAbertura == null) {
			throw new ErroDeValidacaoDeCamposException("O campo dataDeAbertura não pode ser nulo");
		}
		this.codigo = codigo;
		this.cursoID = cursoID;
		this.dataDeAbertura = dataDeAbertura;
	}
}
