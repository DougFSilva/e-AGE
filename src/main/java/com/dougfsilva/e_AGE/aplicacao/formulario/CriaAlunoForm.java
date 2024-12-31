package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Responsavel;

public record CriaAlunoForm(String nome, Sexo sexo, String CPF, String telefone, String email, LocalDate dataDeNascimento,
		CriaEnderecoForm endereco, Responsavel responsavel, String empresaID) {

	public CriaAlunoForm {
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
	}
}