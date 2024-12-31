package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record MatriculaAlunoForm(String registro, String turmaID, String alunoID,
		LocalDate dataDaMatricula) {

	public MatriculaAlunoForm{
		if (registro == null || registro.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo registro não pode ser nulo ou vazio");
		}
		if (turmaID == null || turmaID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID da turma não pode ser nulo ou vazio");
		}
		if (alunoID == null || alunoID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID do aluno não pode ser nulo ou vazio");
		}
		if (dataDaMatricula == null) {
			throw new ErroDeValidacaoDeCamposException("O campo data da matrícula não pode ser nulo");
		}
	}
}
