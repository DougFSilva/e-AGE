package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.UsuarioResposta;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaUsuario {

	private UsuarioRepository repository;

	public UsuarioResposta buscaPeloID(String ID) {
		return UsuarioResposta.deUsuario(repository.buscarPeloIDOuThrow(ID));
	}

	public Pagina<UsuarioResposta> buscarPeloTipoDePerfil(TipoPerfil tipo, RequisicaoDePagina requisicao){
		return UsuarioResposta.dePagina(repository.buscarPeloTipoDePerfil(tipo, requisicao));
	}

	public Pagina<UsuarioResposta> buscarTodos(RequisicaoDePagina requisicao){
		return UsuarioResposta.dePagina(repository.buscarTodos(requisicao));
	}

}
