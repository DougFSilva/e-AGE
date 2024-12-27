package com.dougfsilva.e_AGE.dominio.pessoa.funcionario;

import java.time.LocalDate;

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
public class Funcionario extends Pessoa {

	private String registro;
	private Cargo cargo;
	private Boolean ativo;
	
	public Funcionario(String nome, Sexo sexo, String RG, String telefone, Email email, LocalDate dataDeNascimento,
			Endereco endereco, String foto, String registro, Cargo cargo) {
		super(nome, sexo, RG, telefone, email, dataDeNascimento, endereco, foto);
		this.registro = registro;
		this.cargo = cargo;
		this.ativo = true;
	}
}
