package com.dougfsilva.e_AGE.aplicacao.dto;

import java.util.List;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

public record EditaUsuarioForm(String ID, String nomeDeUsuario, List<TipoPerfil> tipoPerfil) {

	public EditaUsuarioForm(String ID, String nomeDeUsuario, List<TipoPerfil> tipoPerfil) {
		if (ID == null || ID.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
		}
		this.ID = ID;
		this.nomeDeUsuario = nomeDeUsuario;
		this.tipoPerfil = tipoPerfil;
	}
}
