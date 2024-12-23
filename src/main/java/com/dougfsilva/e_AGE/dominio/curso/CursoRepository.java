package com.dougfsilva.e_AGE.dominio.curso;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface CursoRepository {

	Curso salvar(Curso curso);

	void remover(Curso curso);

	Optional<Curso> buscarPeloID(String ID);

	default Curso buscarPeloIDOuExcecao(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Curso com ID %s não encontrado", ID)));
	}
	
	Pagina<Curso> buscarTodosPelaModalidade(Modalidade modalidade, RequisicaoDePagina requisicao);
	
	Pagina<Curso> buscarTodosPelaAreaTecnologica(AreaTecnologica area, RequisicaoDePagina requisicao);
	
	Pagina<Curso> buscarTodosPeloTituloContem(String titulom, RequisicaoDePagina requisicao);
	
	Pagina<Curso> buscarTodosPeloStatusAberto(Boolean aberto, RequisicaoDePagina requisicao);
	
	Pagina<Curso> buscarTodos(RequisicaoDePagina requisicao);
}