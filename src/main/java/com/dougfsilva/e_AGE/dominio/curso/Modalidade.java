package com.dougfsilva.e_AGE.dominio.curso;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Modalidade {

	PRESENCIAL("Aulas presenciais em uma sala de aula física"), 
	SEMI_PRESENCIAL("Combina aulas online e presenciais"),
	ENSINO_A_DISTANCIA("Totalmente online, com aulas virtuais");

	private String descricao;

	private Modalidade(String descricao) {
		this.descricao = descricao;
	}

}
