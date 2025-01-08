package com.dougfsilva.e_AGE.dominio.ocorrencia;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum OcorrenciaStatus {

	ABERTA("Ocorrência aberta"),
	FECHADA("Ocorrência tratada e fechada"),
	ASSINADA("Ocorrência assinada pelo aluno"),
	ENCERRADA("Ocorrência finalizada"),
	BLOQUEADA("Ocorrência bloqueada");

	private String descricao;

	private OcorrenciaStatus(String descricao) {
	    this.descricao = descricao;
	}
}
