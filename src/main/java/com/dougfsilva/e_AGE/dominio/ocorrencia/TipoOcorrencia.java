package com.dougfsilva.e_AGE.dominio.ocorrencia;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TipoOcorrencia {

	CONDUTA("Mau comportamento ou comportamento inadequado"),
	FALTA("Excesso de faltas"),
	ATRASO("Chegada tardia para aulas ou atividades"),
	DESEMPENHO("Desempenho acadêmico ou em atividades insatisfatório"),
	OCORRENCIA_POSITIVA("Reconhecimento de bom comportamento ou desempenho"),
	OUTRO("Ocorrência não categorizada");

	private String descricao;

	private TipoOcorrencia(String descricao) {
	    this.descricao = descricao;
	}
}
