package com.dougfsilva.e_AGE.dominio.pessoa.usuario;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface UsuarioRepository {

	Usuario salvar(Usuario usuario);
	
	void excluir(Usuario usuario);
	
	Optional<Usuario> buscarPeloID(String ID);
	
	default Usuario buscarPeloIDOuThrow(String ID) {
	    return buscarPeloID(ID)
	        .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Usuário com ID %s não encontrado", ID)));
	}
	
	Pagina<Usuario> buscarPeloTipoDePerfil(TipoPerfil tipo, RequisicaoDePagina requisicao);
	
	Pagina<Usuario> buscarTodos(RequisicaoDePagina requisicao);
	
	Boolean existePeloNomeDoUsuario(String username);
}
