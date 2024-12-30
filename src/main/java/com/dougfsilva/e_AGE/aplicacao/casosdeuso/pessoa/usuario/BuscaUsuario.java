package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario;

import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaUsuario {

	private UsuarioRepository repository;

	public Usuario buscaPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	public Pagina<Usuario> buscarPeloTipoDePerfil(TipoPerfil tipo, RequisicaoDePagina requisicao){
		return repository.buscarPeloTipoDePerfil(tipo, requisicao);
	}

	public Pagina<Usuario> buscarTodos(RequisicaoDePagina requisicao){
		return repository.buscarTodos(requisicao);
	}

}
