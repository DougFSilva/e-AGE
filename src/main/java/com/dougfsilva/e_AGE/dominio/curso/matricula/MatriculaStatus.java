package com.dougfsilva.e_AGE.dominio.curso.matricula;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MatriculaStatus {

	ATIVA("Aluno com matricula ativa no módulo"),
	CONCLUIDA("Aluno concluiu o módulo"),
	REPROVADA("Aluno foi reprovado no módulo"),
	EVADIDA("Aluno evadiu do curso");
	
	private String descricao;

	private MatriculaStatus(String descricao) {
		this.descricao = descricao;
	}
	
}
