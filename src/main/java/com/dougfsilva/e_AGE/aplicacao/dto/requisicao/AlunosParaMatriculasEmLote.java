package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record AlunosParaMatriculasEmLote(String registro, String alunoID,
		LocalDate dataDaMatricula) {

	public AlunosParaMatriculasEmLote(String registro, String alunoID, LocalDate dataDaMatricula) {
		if (registro == null || registro.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo registro não pode ser nulo ou vazio");
		}
		if (alunoID == null || alunoID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID do aluno não pode ser nulo ou vazio");
		}
		if (dataDaMatricula == null) {
			throw new ErroDeValidacaoDeCamposException("O campo data da matrícula não pode ser nulo");
		}
		this.registro = registro;
		this.alunoID = alunoID;
		this.dataDaMatricula = dataDaMatricula;
	}
}
