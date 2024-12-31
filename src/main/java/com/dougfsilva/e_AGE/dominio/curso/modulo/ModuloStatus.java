package com.dougfsilva.e_AGE.dominio.curso.modulo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ModuloStatus {

	NAO_INICIADO("O módulo ainda não foi iniciado"),
	INICIADO("O módulo foi iniciado"),
	CONCLUIDO("O módulo foi concluído");
	
	private String descricao;
	
	ModuloStatus(String descricao) {
		this.descricao = descricao;
	}
}
