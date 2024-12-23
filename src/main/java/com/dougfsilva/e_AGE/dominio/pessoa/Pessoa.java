package com.dougfsilva.e_AGE.dominio.pessoa;

import java.time.LocalDate;
import java.time.Period;

import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "RG" })
public class Pessoa {

	private String ID;
	private Usuario usuario;
	private String nome;
	private Sexo sexo;
	private String RG;
	private String telefone;
	private Email email;
	private LocalDate dataDeNascimento;
	private Endereco endereco;
	private String foto;

	public Pessoa(String nome, Sexo sexo, String RG, String telefone, Email email, LocalDate dataDeNascimento,
			Endereco endereco, String foto) {
		this.nome = nome;
		this.sexo = sexo;
		this.RG = RG;
		this.telefone = telefone;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.endereco = endereco;
		this.foto = foto;
	}

	public Integer calcularIdade() {
		return Period.between(this.dataDeNascimento, LocalDate.now()).getYears();
	}

}
