package com.dougfsilva.e_AGE.dominio.curso.matricula.alunoprogresso;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AlunoProgresso {

	private String ID;
	private Matricula matricula;
	private Modulo modulo;
	private ProgressoStatus status;
	
	public AlunoProgresso(Matricula matricula, Modulo modulo, ProgressoStatus status) {
		this.matricula = matricula;
		this.modulo = modulo;
		this.status = status;
	}
}
