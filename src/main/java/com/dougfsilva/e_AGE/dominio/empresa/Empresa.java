package com.dougfsilva.e_AGE.dominio.empresa;

import com.dougfsilva.e_AGE.dominio.pessoa.Endereco;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "CNPJ" })
public class Empresa {

	private String ID;
	private String CNPJ;
	private String nome;
	private Endereco endereco;

	public Empresa(String cNPJ, String nome, Endereco endereco) {
		CNPJ = cNPJ;
		this.nome = nome;
		this.endereco = endereco;
	}

}
