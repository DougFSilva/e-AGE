package com.dougfsilva.e_AGE.dominio.curso.certificado;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "matricula" })
public class Certificado {

	private String ID;
	private Matricula matricula;
	private LocalDate data;
	
	public Certificado(Matricula matricula, LocalDate data) {
		this.matricula = matricula;
		this.data = data;
	}
}
