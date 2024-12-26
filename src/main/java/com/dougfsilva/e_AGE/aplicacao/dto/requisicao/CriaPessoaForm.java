package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;

public record CriaPessoaForm(String nome, Sexo sexo, String RG, String telefone, String email,
		LocalDate dataDeNascimento, CriaEnderecoForm endereco) {

	public CriaPessoaForm(String nome, Sexo sexo, String RG, String telefone, String email, LocalDate dataDeNascimento,
			CriaEnderecoForm endereco) {
		if (nome == null || nome.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo nome não pode ser nulo ou vazio");
		}
		if (sexo == null) {
			throw new ErroDeValidacaoDeCamposException("O campo sexo não pode ser nulo");
		}
		if (RG == null || RG.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo RG não pode ser nulo ou vazio");
		}
		if (telefone == null || telefone.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo telefone não pode ser nulo ou vazio");
		}
		if (email == null || email.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo email não pode ser nulo ou vazio");
		}
		if (dataDeNascimento == null) {
			throw new ErroDeValidacaoDeCamposException("O campo data de nascimento não pode ser nulo");
		}
		if (endereco == null) {
			throw new ErroDeValidacaoDeCamposException("O campo endereço não pode ser nulo");
		}
		this.nome = nome;
		this.sexo = sexo;
		this.RG = RG;
		this.telefone = telefone;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.endereco = endereco;
	}

}
