package com.dougfsilva.e_AGE.aplicacao.dto;

import java.util.List;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record MatriculaAlunosForm(String moduloID, List<AlunosParaMatriculasEmLote> alunos) {

	public MatriculaAlunosForm(String moduloID, List<AlunosParaMatriculasEmLote> alunos) {
		if (moduloID == null || moduloID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID do módulo não pode ser nulo ou vazio");
		}
		if (alunos == null) {
			throw new ErroDeValidacaoDeCamposException("O campo alunos não pode ser nulo");
		}
		this.moduloID = moduloID;
		this.alunos = alunos;
	}
}
