package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeUsuarioException;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaUsuario {

	private final UsuarioRepository repository;
	
	public void validarUnicoNomeDeUsuario(String nomeDeUsuario) {
		if (repository.existePeloNomeDoUsuario(nomeDeUsuario)) {
			throw new ErroDeValidacaoDeUsuarioException(String.format("Nome de usuário %s já existe na base de dados", nomeDeUsuario));
		}
	}
}
