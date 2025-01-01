package com.dougfsilva.e_AGE.dominio.curso.modulo;

import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "codigo", "turma" })
@ToString
public class Modulo {

	private String ID;
	private String codigo;
	private Turma turma;
	private ModuloStatus status;
	private Integer numeroDoModulo;

	public Modulo(String codigo, Turma turma, Integer numeroDoModulo, ModuloStatus status) {
		this.codigo = codigo;
		this.turma = turma;
		this.numeroDoModulo = numeroDoModulo;
		this.status = status;
	}
}
