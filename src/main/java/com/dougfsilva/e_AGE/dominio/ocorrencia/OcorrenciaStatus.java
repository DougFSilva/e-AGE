package com.dougfsilva.e_AGE.dominio.ocorrencia;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum OcorrenciaStatus {

	ABERTA("A ocorrência foi aberta"),
	FECHADA("A ocorrência foi tratada e fechada"),
	ASSINADA("A ocorrência foi assinada pelo aluno"),
	ENCERRADA("A ocorrência foi finalizada");

	private String descricao;

	private OcorrenciaStatus(String descricao) {
	    this.descricao = descricao;
	}
}
