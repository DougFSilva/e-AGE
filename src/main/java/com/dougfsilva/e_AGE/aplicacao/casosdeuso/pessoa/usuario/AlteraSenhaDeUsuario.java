package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.exception.SenhaDeUsuarioInvalidaException;
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
	private final LogPadrao log;
	
	public void alterar(String senhaAtual, String novaSenha) {
		try {
			Usuario usuario = usuarioAutenticado.buscarUsuarioAtualOuThrow();
			compararSenha(senhaAtual, usuario.getSenha().getSenha());
			usuario.setSenha(new SenhaDeUsuario(novaSenha, codificador));
			repository.salvar(usuario);
			log.info(String.format("Senha do usuário %s atualizada", usuario));
		} catch (ErroDeValidacaoDeUsuarioException | ObjetoNaoEncontradoException | SenhaDeUsuarioInvalidaException e) {
			String mensagem = String.format("Erro ao atualizar senha de usuário : %s", e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComUsuarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao atualizar senha de usuário : %s", e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComUsuarioException(mensagem, e);
		}
	}
	
	private void compararSenha(String senhaAtual, String senhaCodificada) {
		if (!codificador.comparar(senhaAtual, senhaCodificada)) {
			throw new ErroDeValidacaoDeUsuarioException("A senha atual está errada");
		}
	}
}
