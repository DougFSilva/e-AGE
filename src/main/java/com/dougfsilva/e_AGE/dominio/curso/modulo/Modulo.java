package com.dougfsilva.e_AGE.dominio.curso.modulo;

import com.dougfsilva.e_AGE.dominio.curso.Curso;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "codigo", "curso" })
public class Modulo {

	private String ID;
	private String codigo;
	private Curso curso;

	public Modulo(String codigo, Curso curso) {
		this.codigo = codigo;
		this.curso = curso;
	}

}
