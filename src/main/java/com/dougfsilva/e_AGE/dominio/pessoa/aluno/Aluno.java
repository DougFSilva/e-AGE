package com.dougfsilva.e_AGE.dominio.pessoa.aluno;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.Pessoa;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Aluno extends Pessoa {

	private Responsavel responsavel;
	private Empresa empresa;

	public Aluno(String nome, Sexo sexo, String RG, String telefone, Email email, LocalDate dataDeNascimento,
			Endereco endereco, String foto) {
		super(nome, sexo, RG, telefone, email, dataDeNascimento, endereco, foto);
	}
}
