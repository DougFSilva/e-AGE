package com.dougfsilva.e_AGE.dominio.curso.reprova;

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
@EqualsAndHashCode
@ToString
public class Reprova {

	private String ID;
	private Aluno aluno;
	private Turma turma;
	private CausaReprova causa;

	public Reprova(Aluno aluno, Turma turma, CausaReprova causa) {
		this.aluno = aluno;
		this.turma = turma;
		this.causa = causa;
	}
}
