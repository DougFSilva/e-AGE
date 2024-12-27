package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;

public record EditaPessoaForm(String nome, Sexo sexo, String RG, String telefone, String email,
		LocalDate dataDeNascimento, EditaEnderecoForm endereco) {

}
