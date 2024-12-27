package com.dougfsilva.e_AGE.dominio.curso.modulo;

import java.time.LocalDate;

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
	private Boolean aberto;
	private LocalDate dataDeAbertura;
	private LocalDate dataDeFechamento;

	public Modulo(String codigo, Turma turma, LocalDate dataDeAbertura) {
		this.codigo = codigo;
		this.turma = turma;
		this.aberto = true;
		this.dataDeAbertura = dataDeAbertura;
	}
}
