package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiUsuario {

	private final UsuarioRepository repository;
	private final LogPadrao log;

	public void excluirPeloID(String ID) {
		try {
			Usuario usuario = repository.buscarPeloIDOuThrow(ID);
			repository.excluir(usuario);
			log.info(String.format("Usuário %s excluido", usuario.getNomeDeUsuario()));
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao excluir usuário com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComUsuarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir usuário com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComUsuarioException(mensagem, e);
		}
	}
}
