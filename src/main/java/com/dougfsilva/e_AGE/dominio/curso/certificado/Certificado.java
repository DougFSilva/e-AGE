package com.dougfsilva.e_AGE.dominio.curso.certificado;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "aluno", "curso" })
public class Certificado {

	private String ID;
	private Aluno aluno;
	private Curso curso;
	private LocalDate dataDaCertificacao;

	public Certificado(Aluno aluno, Curso curso, LocalDate dataDaCertificacao) {
		this.aluno = aluno;
		this.curso = curso;
		this.dataDaCertificacao = dataDaCertificacao;
	}

}
