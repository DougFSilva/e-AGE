package com.dougfsilva.e_AGE.dominio.curso.matricula;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "turma", "aluno" })
@ToString
public class Matricula {

	private String ID;
	private String registro;
	private Turma turma;
	private Aluno aluno;
	private LocalDate dataDaMatricula;
	private MatriculaStatus status;
	
	public Matricula(String registro, Turma turma, Aluno aluno, LocalDate dataDaMatricula, MatriculaStatus status) {
		this.registro = registro;
		this.turma = turma;
		this.aluno = aluno;
		this.dataDaMatricula = dataDaMatricula;
		this.status = status;
	}
}
