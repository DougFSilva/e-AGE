package com.dougfsilva.e_AGE.dominio.curso;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface CursoRepository {

	Curso salvar(Curso curso);

	void excluir(Curso curso);

	Optional<Curso> buscarPeloID(String ID);

	default Curso buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Curso com ID %s não encontrado", ID)));
	}
	
	Pagina<Curso> buscarPelaModalidade(Modalidade modalidade, RequisicaoDePagina requisicao);
	
	Pagina<Curso> buscarPelaAreaTecnologica(AreaTecnologica area, RequisicaoDePagina requisicao);
	
	Pagina<Curso> buscarPeloTituloContem(String titulom, RequisicaoDePagina requisicao);
	
	Pagina<Curso> buscarPeloStatusAberto(Boolean aberto, RequisicaoDePagina requisicao);
	
	Pagina<Curso> buscarTodos(RequisicaoDePagina requisicao);
	
	Boolean existePeloTitulo(String titulo);
	
	Boolean existePelaAreaTecnologica(AreaTecnologica area);
}
