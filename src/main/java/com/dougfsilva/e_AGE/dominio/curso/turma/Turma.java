package com.dougfsilva.e_AGE.dominio.curso.turma;

import com.dougfsilva.e_AGE.dominio.curso.Curso;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "codigo", "curso" })
@ToString
public class Turma {

	private String ID;
	private String codigo;
	private Curso curso;
	private Boolean aberta;

	public Turma(String codigo, Curso curso, Boolean aberta) {
		this.codigo = codigo;
		this.curso = curso;
		this.aberta = aberta;
	}

}
