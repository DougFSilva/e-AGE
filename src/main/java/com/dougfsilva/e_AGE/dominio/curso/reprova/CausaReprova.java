package com.dougfsilva.e_AGE.dominio.curso.reprova;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CausaReprova {

	APROVEITAMENTO("Aluno com aproveitamento abaixo do mínimo"),
	FREQUENCIA("Aluno com frequência abaixo do mínimo");
	
	private String descricao;
	
	private CausaReprova(String descricao) {
		this.descricao = descricao;
	}
}
