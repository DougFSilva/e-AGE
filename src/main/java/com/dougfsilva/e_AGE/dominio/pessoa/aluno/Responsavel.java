package com.dougfsilva.e_AGE.dominio.pessoa.aluno;

import com.dougfsilva.e_AGE.dominio.pessoa.Email;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "name", "email" })
@ToString
public class Responsavel {

	private String name;
	private Email email;
	private String telefone;

}
