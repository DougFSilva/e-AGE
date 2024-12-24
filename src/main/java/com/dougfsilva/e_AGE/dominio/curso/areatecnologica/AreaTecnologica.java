package com.dougfsilva.e_AGE.dominio.curso.areatecnologica;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "titulo" })
@ToString
public class AreaTecnologica {

	private String ID;
	private String titulo;
	private String descricao;
	private String imagem;

	public AreaTecnologica(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
	}

}
