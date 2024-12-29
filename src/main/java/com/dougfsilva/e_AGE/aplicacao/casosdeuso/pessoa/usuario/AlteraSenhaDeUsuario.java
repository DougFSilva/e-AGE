package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.UsuarioResposta;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeUsuarioException;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.CodificadorDeSenha;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.SenhaDeUsuario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.UsuarioAutenticado;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlteraSenhaDeUsuario {

	private final UsuarioRepository repository;
	private final UsuarioAutenticado usuarioAutenticado;
	private final CodificadorDeSenha codificador;
	
	public UsuarioResposta alterar(String senhaAtual, String novaSenha) {
		Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
		compararSenha(senhaAtual, usuario.getSenha().getSenha());
		usuario.setSenha(new SenhaDeUsuario(novaSenha, codificador));
		return UsuarioResposta.deUsuario(repository.salvar(usuario));
	}
	
	private void compararSenha(String senhaAtual, String senhaCodificada) {
		if (!codificador.comparar(senhaAtual, senhaCodificada)) {
			throw new ErroDeValidacaoDeUsuarioException("A senha atual está errada");
		}
	}
}
