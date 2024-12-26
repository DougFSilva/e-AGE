package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaUsuarioForm;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaUsuario {

	private final UsuarioRepository repository;
	private final ValidaUsuario validador;
	private final LogPadrao log;
	
	public Usuario editar(EditaUsuarioForm form) {
		try {
			Usuario usuario = repository.buscarPeloIDOuThrow(form.ID());
			Usuario usuarioEditado = editarDados(form, usuario);
			Usuario usuarioSalvo = repository.salvar(usuarioEditado);
			log.info(String.format("Editado usuário %s", usuarioSalvo.getNomeDeUsuario()));
			return usuario;
		} catch (ErroDeValidacaoDeUsuarioException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao editar usuário %s : %s", form.nomeDeUsuario(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComUsuarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar usuário %s : %s", form.nomeDeUsuario(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComUsuarioException(mensagem, e);
		}
	}
	
	private Usuario editarDados(EditaUsuarioForm form, Usuario usuario) {
		if (form.nomeDeUsuario() != null && !form.nomeDeUsuario().isBlank() && !form.nomeDeUsuario().equalsIgnoreCase(usuario.getNomeDeUsuario())) {
			validador.validarUnicoNomeDeUsuario(form.nomeDeUsuario());
			usuario.setNomeDeUsuario(form.nomeDeUsuario());
		}
		if (form.tipoPerfil() != null) {
			usuario.getPerfis().clear();
			form.tipoPerfil().forEach(tipo -> usuario.adicionarPerfil(tipo));
		}
		return usuario;
	}
}
