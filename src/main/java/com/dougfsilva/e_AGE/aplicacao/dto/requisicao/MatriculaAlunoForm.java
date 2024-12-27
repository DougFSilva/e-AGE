package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record MatriculaAlunoForm(String registro, String turmaID, String moduloID, String alunoID,
		LocalDate dataDaMatricula, MatriculaStatus status) {

	public MatriculaAlunoForm(String registro, String turmaID, String moduloID, String alunoID,
			LocalDate dataDaMatricula, MatriculaStatus status) {
		if (registro == null || registro.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo registro não pode ser nulo ou vazio");
		}
		if (turmaID == null || turmaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID da turma não pode ser nulo ou vazio");
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
		if (status == null) {
			throw new ErroDeValidacaoDeCamposException("O campo status não pode ser nulo");
		}

		this.registro = registro;
		this.turmaID = turmaID;
		this.moduloID = moduloID;
		this.alunoID = alunoID;
		this.dataDaMatricula = dataDaMatricula;
		this.status = status;
	}
}
