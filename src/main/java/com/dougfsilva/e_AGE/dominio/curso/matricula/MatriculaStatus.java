package com.dougfsilva.e_AGE.dominio.curso.matricula;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MatriculaStatus {

	ALUNO_MATRICULADO("Aluno está matriculado no curso"),
	ALUNO_CERTIFICADO("Aluno foi certificado no curso"),
	ALUNO_REPROVADO("Aluno foi reprovado no curso"),
	ALUNO_EVADIDO("Aluno evadiu do curso");
	
	private String descricao;

	private MatriculaStatus(String descricao) {
		this.descricao = descricao;
	}
	
}
