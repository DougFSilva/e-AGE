package com.dougfsilva.e_AGE.dominio.empresa;

import com.dougfsilva.e_AGE.dominio.endereco.Endereco;

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

	public Empresa(String CNPJ, String nome, Endereco endereco) {
		this.CNPJ = CNPJ;
		this.nome = nome;
		this.endereco = endereco;
	}

}
