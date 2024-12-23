package com.dougfsilva.e_AGE.dominio.utilidades.imagem;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TipoDeImagem {

	AREA_TECNOLOGICA("Área tecnologica"), 
	CURSO("Curso"), 
	ALUNO("Aluno"), 
	EMPREGADO("Empregado");

	private String descricao;

	private TipoDeImagem(String descricao) {
		this.descricao = descricao;
	}
}
