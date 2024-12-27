package com.dougfsilva.e_AGE.dominio.curso.matricula;

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
@EqualsAndHashCode(of = { "modulo", "aluno" })
@ToString
public class Matricula {

	private String ID;
	private String registro;
	private Modulo modulo;
	private Aluno aluno;
	private LocalDate dataDaMatricula;
	private MatriculaStatus status;

	public Matricula(String registro, Modulo modulo, Aluno aluno, LocalDate dataDaMatricula, MatriculaStatus status) {
		this.registro = registro;
		this.modulo = modulo;
		this.aluno = aluno;
		this.dataDaMatricula = dataDaMatricula;
		this.status = status;
	}

}
