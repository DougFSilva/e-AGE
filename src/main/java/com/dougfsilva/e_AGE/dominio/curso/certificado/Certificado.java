package com.dougfsilva.e_AGE.dominio.curso.certificado;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "aluno", "turma" })
public class Certificado {

	private String ID;
	private Aluno aluno;
	private Turma turma;
	private LocalDate dataDaCertificacao;

	public Certificado(Aluno aluno, Turma turma, LocalDate dataDaCertificacao) {
		this.aluno = aluno;
		this.turma = turma;
		this.dataDaCertificacao = dataDaCertificacao;
	}

}
