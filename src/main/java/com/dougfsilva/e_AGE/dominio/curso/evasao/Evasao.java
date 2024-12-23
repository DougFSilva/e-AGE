package com.dougfsilva.e_AGE.dominio.curso.evasao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.matricula.Matricula;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "matricula" })
@ToString
public class Evasao {

	private String ID;
	private Matricula matricula;
	private String motivo;
	private LocalDate data;
}
