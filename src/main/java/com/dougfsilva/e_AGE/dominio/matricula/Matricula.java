package com.dougfsilva.e_AGE.dominio.matricula;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "turma", "modulo" })
public class Matricula {

	private String ID;
	private String registro;
	private Turma turma;
	private Modulo modulo;
	private LocalDate dataDaMatricula;
	private MatriculaStatus status;

	public Matricula(String registro, Turma turma, Modulo modulo, LocalDate dataDaMatricula,
			MatriculaStatus status) {
		this.registro = registro;
		this.turma = turma;
		this.modulo = modulo;
		this.dataDaMatricula = dataDaMatricula;
		this.status = status;
	}

}
