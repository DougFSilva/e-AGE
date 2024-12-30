package com.dougfsilva.e_AGE.aplicacao.dto;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaCertificadoForm(String alunoID, String cursoID, LocalDate dataDaCertificacao) {

	 public CriaCertificadoForm(String alunoID, String cursoID, LocalDate dataDaCertificacao) {
	        if (alunoID == null || alunoID.isBlank()) {
	            throw new ErroDeValidacaoDeCamposException("O campo ID do aluno não pode ser nulo ou vazio");
	        }
	        if (cursoID == null || cursoID.isBlank()) {
	            throw new ErroDeValidacaoDeCamposException("O campo ID do curso não pode ser nulo ou vazio");
	        }
	        if (dataDaCertificacao == null) {
	            throw new ErroDeValidacaoDeCamposException("O campo data da certificacão não pode ser nulo");
	        }
	        this.alunoID = alunoID;
	        this.cursoID = cursoID;
	        this.dataDaCertificacao = dataDaCertificacao;
	    }
}
