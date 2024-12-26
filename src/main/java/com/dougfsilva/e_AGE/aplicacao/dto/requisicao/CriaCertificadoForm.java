package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaCertificadoForm(String alunoID, String cursoID, LocalDate dataDaCertificacao) {

	 public CriaCertificadoForm(String alunoID, String cursoID, LocalDate dataDaCertificacao) {
	        if (alunoID == null || alunoID.isBlank()) {
	            throw new ErroDeValidacaoDeCamposException("O campo alunoID não pode ser nulo ou vazio");
	        }
	        if (cursoID == null || cursoID.isBlank()) {
	            throw new ErroDeValidacaoDeCamposException("O campo cursoID não pode ser nulo ou vazio");
	        }
	        if (dataDaCertificacao == null) {
	            throw new ErroDeValidacaoDeCamposException("O campo dataDaCertificacao não pode ser nulo");
	        }
	        this.alunoID = alunoID;
	        this.cursoID = cursoID;
	        this.dataDaCertificacao = dataDaCertificacao;
	    }
}
