package com.dougfsilva.e_AGE.dominio.utilidades;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeUsuarioException;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;

public interface UsuarioAutenticado {

	Optional<Usuario> buscarUsuarioAtual();

	default Usuario buscarUsuarioAtualOuThrow() {
		return buscarUsuarioAtual().orElseThrow(() -> new ErroDeUsuarioException("Nenhum usuario autenticado"));
	}
}
