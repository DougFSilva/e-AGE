package com.dougfsilva.e_AGE.dominio.utilidades.paginacao;

import lombok.Getter;

@Getter
public enum Direcao {

	ASC("Direção ascendente"), DESC("Direção descendente");

	private String descricao;

	private Direcao(String descricao) {
		this.descricao = descricao;
	}
}
