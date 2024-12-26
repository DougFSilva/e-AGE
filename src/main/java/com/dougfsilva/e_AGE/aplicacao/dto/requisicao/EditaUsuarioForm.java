package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.util.List;

import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

public record EditaUsuarioForm(String ID, String nomeDeUsuario, List<TipoPerfil> tipoPerfil) {
	
}
