package com.dougfsilva.e_AGE.aplicacao.dto;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record MatriculaAlunoForm(String registro, String moduloID, String alunoID,
		LocalDate dataDaMatricula) {

	public MatriculaAlunoForm(String registro, String moduloID, String alunoID,
			LocalDate dataDaMatricula) {
		if (registro == null || registro.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo registro não pode ser nulo ou vazio");
		}
		if (moduloID == null || moduloID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID do módulo não pode ser nulo ou vazio");
		}
		if (alunoID == null || alunoID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID do aluno não pode ser nulo ou vazio");
		}
		if (dataDaMatricula == null) {
			throw new ErroDeValidacaoDeCamposException("O campo data da matrícula não pode ser nulo");
		}
		this.registro = registro;
		this.moduloID = moduloID;
		this.alunoID = alunoID;
		this.dataDaMatricula = dataDaMatricula;
	}
}
