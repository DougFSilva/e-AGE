package com.dougfsilva.e_AGE.dominio.pessoa;

import lombok.Getter;

@Getter
public enum Sexo {


	MASCULINO("Sexo masculino"), FEMININO("Sexo feminino"), INTERSEXO("Intersexo"), NAO_REVELADO("Sexo não revelado");

	private final String descricao;

	Sexo(String descricao) {
		this.descricao = descricao;
	}

	public static Sexo pelaLetra(String letra) {
		if (letra == null || letra.isBlank()) {
			throw new IllegalArgumentException("O valor não pode ser nulo ou vazio");
		}
		switch (letra.trim().toUpperCase()) {
		case "M":
			return MASCULINO;
		case "F":
			return FEMININO;
		case "I":
			return INTERSEXO;
		case "N":
			return NAO_REVELADO;
		default:
			throw new IllegalArgumentException("Letra inválida: " + letra);
		}
	}
}
