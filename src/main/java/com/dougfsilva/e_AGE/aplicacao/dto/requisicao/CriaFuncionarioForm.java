package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Cargo;

public record CriaFuncionarioForm(String nome, Sexo sexo, String CPF, String telefone, String email, LocalDate dataDeNascimento,
		CriaEnderecoForm endereco, String registro, Cargo cargo) {

	public CriaFuncionarioForm(String nome, Sexo sexo, String CPF, String telefone, String email, LocalDate dataDeNascimento,
	CriaEnderecoForm endereco, String registro, Cargo cargo) {
		if (nome == null || nome.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo nome não pode ser nulo ou vazio");
		}
		if (sexo == null) {
			throw new ErroDeValidacaoDeCamposException("O campo sexo não pode ser nulo");
		}
		if (CPF == null || CPF.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo CPF não pode ser nulo ou vazio");
		}
		if (telefone == null || telefone.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo telefone não pode ser nulo ou vazio");
		}
		if (email == null || email.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo email não pode ser nulo");
		}
		if (dataDeNascimento == null) {
			throw new ErroDeValidacaoDeCamposException("O campo data de nascimento não pode ser nulo");
		}
		if (endereco == null) {
			throw new ErroDeValidacaoDeCamposException("O campo endereço não pode ser nulo");
		}
		if (registro == null || registro.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo registro não pode ser nulo ou vazio");
		}
		if (cargo == null) {
			throw new ErroDeValidacaoDeCamposException("O campo cargo não pode ser nulo");
		}
		this.nome = nome;
		this.sexo = sexo;
		this.CPF = CPF;
		this.telefone = telefone;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.endereco = endereco;
		this.registro = registro;
		this.cargo = cargo;
	}
}