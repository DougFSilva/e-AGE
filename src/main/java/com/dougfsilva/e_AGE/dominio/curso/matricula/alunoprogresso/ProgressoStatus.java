package com.dougfsilva.e_AGE.dominio.curso.matricula.alunoprogresso;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ProgressoStatus {

	INICIADO("O módulo iniciou o módulo"),
	REPROVADO("O aluno foi reprovado no módulo"),
	EVADIDO("O aluno foi evadido do curso");
	
	private String descricao;
	
	private ProgressoStatus(String descricao) {
		this.descricao = descricao;
	}
}
