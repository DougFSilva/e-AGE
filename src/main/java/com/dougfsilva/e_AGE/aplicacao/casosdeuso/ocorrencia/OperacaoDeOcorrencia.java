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
	NOVO_PIN_GERADO("Novo PIN reenviado");
	
	private String descricao;
	
	private OperacaoDeOcorrencia(String descricao) {
		this.descricao = descricao;
	}
}
