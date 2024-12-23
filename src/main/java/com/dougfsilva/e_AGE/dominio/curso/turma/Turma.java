package com.dougfsilva.e_AGE.dominio.curso.turma;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.Curso;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "codigo", "curso" })
public class Turma {

	private String ID;
	private String codigo;
	private Curso curso;
	private Boolean aberta;
	private LocalDate dataDeAbertura;
	private LocalDate dataDeFechamento;

	public Turma(String codigo, Curso curso, Boolean aberta) {
		this.codigo = codigo;
		this.curso = curso;
		this.aberta = aberta;
		this.dataDeAbertura = LocalDate.now();
	}

}
