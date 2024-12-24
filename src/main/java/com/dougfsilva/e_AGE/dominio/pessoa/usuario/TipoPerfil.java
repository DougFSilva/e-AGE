package com.dougfsilva.e_AGE.dominio.pessoa.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum TipoPerfil {

	ADMIN(1, "ROLE_ADMIN"), 
	GESTOR(2, "ROLE_GESTOR"), 
	PROFESSOR(3, "ROLE_PROFESSOR"),
	FUNCIONARIO(4, "ROLE_FUNCIONARIO"), 
	ESTUDANTE(5, "ROLE_ESTUDANTE");

	private long codigo;
	private String descricao;

	public static TipoPerfil paraEnum(String descricao) {
		if (descricao == null) {
			return null;
		}
		for (TipoPerfil x : TipoPerfil.values()) {
			if (descricao.equals(x.getDescricao())) {
				return x;
			}

		}
		throw new IllegalArgumentException("Invalid Profile!");
	}

	public static TipoPerfil peloCodigo(Long codigo) {
		if (codigo != null) {
			for (TipoPerfil x : TipoPerfil.values()) {
				if (x.getCodigo() == codigo) {
					return x;
				}
			}
		}
		throw new IllegalArgumentException("Código de perfil inválido: " + codigo);
	}

}
