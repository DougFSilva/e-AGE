package com.dougfsilva.e_AGE.dominio.curso.reprova;

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
public class Reprova {

	private String ID;
	private Matricula matricula;
	private Modulo modulo;
	private LocalDate data;
	private CausaReprova causa;
	private String detalhes;

	public Reprova(Matricula matricula, Modulo modulo, LocalDate data, CausaReprova causa, String detalhes) {
		this.matricula = matricula;
		this.modulo = modulo;
		this.data = data;
		this.causa = causa;
		this.detalhes = detalhes;
	}
}
