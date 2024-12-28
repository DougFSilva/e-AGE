package com.dougfsilva.e_AGE.dominio.curso.matricula;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MatriculaStatus {

	MATRICULA_ATIVA("Aluno com matricula ativa no módulo"),
	ALUNO_APROVADO("Aluno foi aprovado no módulo"),
	ALUNO_REPROVADO("Aluno foi reprovado no módulo"),
	ALUNO_EVADIDO("Aluno evadiu do curso");
	
	private String descricao;

	private MatriculaStatus(String descricao) {
		this.descricao = descricao;
	}
	
}
