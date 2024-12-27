package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Cargo;

public record EditaFuncionarioForm(String ID, String nome, Sexo sexo, String RG, String telefone, String email, LocalDate dataDeNascimento,
		EditaEnderecoForm endereco, String registro, Cargo cargo) {

	public EditaFuncionarioForm(String ID, String nome, Sexo sexo, String RG, String telefone, String email, LocalDate dataDeNascimento,
			EditaEnderecoForm endereco, String registro, Cargo cargo) {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
		this.ID = ID;
		this.nome = nome;
		this.sexo = sexo;
		this.RG = RG;
		this.telefone = telefone;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.endereco = endereco;
		this.registro = registro;
		this.cargo = cargo;
	}
}