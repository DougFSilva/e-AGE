package com.dougfsilva.e_AGE.aplicacao.dto;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaEMatriculaAlunoForm(String moduloID, LocalDate dataDaMatricula, List<CriaAlunoForm> alunos) {

	public CriaEMatriculaAlunoForm(String moduloID, LocalDate dataDaMatricula, List<CriaAlunoForm> alunos) {
		if (moduloID == null || moduloID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID do módulo não pode ser nulo ou vazio");
		}
		if (dataDaMatricula == null) {
			throw new ErroDeValidacaoDeCamposException("O campo data da matrícula não pode ser nulo");
		}
		if (alunos == null) {
			throw new ErroDeValidacaoDeCamposException("O campo alunos não pode ser nulo");
		}
		this.moduloID = moduloID;
		this.dataDaMatricula = dataDaMatricula;
		this.alunos = alunos;
	}
}