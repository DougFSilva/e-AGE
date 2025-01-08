package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum OperacaoDeOcorrencia {

	ABERTA("Ocorrência aberta"),
	ATUALIZADA("Ocorrência atualizada"),
	FECHADA("Ocorrencia fechada"),
	ENCERRADA("Ocorrencia encerrada"),
	BLOQUEADA("Ocorrencia bloqueada");
	
	private String descricao;
	
	private OperacaoDeOcorrencia(String descricao) {
		this.descricao = descricao;
	}
}
