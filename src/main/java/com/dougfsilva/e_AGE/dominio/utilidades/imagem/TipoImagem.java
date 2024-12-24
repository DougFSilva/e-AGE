package com.dougfsilva.e_AGE.dominio.utilidades.imagem;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TipoImagem {

	AREA_TECNOLOGICA("Área tecnologica"), 
	CURSO("Curso"), 
	ALUNO("Aluno"), 
	EMPREGADO("Empregado");

	private String descricao;

	private TipoImagem(String descricao) {
		this.descricao = descricao;
	}
}
