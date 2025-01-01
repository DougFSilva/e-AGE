package com.dougfsilva.e_AGE.dominio.curso.evasao;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;

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
	private Modulo modulo;
	private LocalDate data;
	private String motivo;

	public Evasao(Matricula matricula, Modulo modulo, LocalDate data, String motivo) {
		this.matricula = matricula;
		this.data = data;
		this.motivo = motivo;
	}
}
