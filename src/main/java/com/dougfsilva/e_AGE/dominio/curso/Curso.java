package com.dougfsilva.e_AGE.dominio.curso;

import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;

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
public class Curso {

	private String ID;
	private Modalidade modalidade;
	private AreaTecnologica areaTecnologica;
	private String titulo;
	private String descricao;
	private Boolean aberto;
	private String imagem;

	public Curso(Modalidade modalidade, AreaTecnologica areaTecnologica, String titulo, String descricao,
			String imagem) {
		this.modalidade = modalidade;
		this.areaTecnologica = areaTecnologica;
		this.titulo = titulo;
		this.descricao = descricao;
		this.aberto = true;
		this.imagem = imagem;
	}

}
