package com.dougfsilva.e_AGE.dominio.curso.evasao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "aluno", "modulo" })
@ToString
public class Evasao {

	private String ID;
	private Aluno aluno;
	private Modulo modulo;
	private LocalDate data;
	private String motivo;

	public Evasao(Aluno aluno, Modulo modulo, LocalDate data, String motivo) {
		this.aluno = aluno;
		this.modulo = modulo;
		this.data = data;
		this.motivo = motivo;
	}
}
