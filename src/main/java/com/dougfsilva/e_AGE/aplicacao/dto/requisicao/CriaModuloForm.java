package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaModuloForm(String codigo, String turmaID, LocalDate dataDeAbertura) {

	public CriaModuloForm(String codigo, String turmaID, LocalDate dataDeAbertura) {
		if (codigo == null || codigo.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo codigo não pode ser nulo ou vazio");
		}
		if (turmaID == null || turmaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo Id da turma não pode ser nulo ou vazio");
		}
		if (dataDeAbertura == null) {
			throw new ErroDeValidacaoDeCamposException("O campo data de abertura não pode ser nulo");
		}
		this.codigo = codigo;
		this.turmaID = turmaID;
		this.dataDeAbertura = dataDeAbertura;
	}
}
