package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Responsavel;

public record EditaAlunoForm(String ID, String nome, Sexo sexo, String CPF, String telefone, String email, LocalDate dataDeNascimento,
		EditaEnderecoForm endereco, Responsavel responsavel, String empresaID) {

	public EditaAlunoForm {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
	}
}
