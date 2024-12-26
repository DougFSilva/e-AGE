package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.CursoResposta;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.Modalidade;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaCurso {

	private final CursoRepository repository;

	public CursoResposta buscaPeloID(String ID) {
		return CursoResposta.deCurso(repository.buscarPeloIDOuThrow(ID));
	}

	public Pagina<CursoResposta> buscarPelaModalidade(Modalidade modalidade, RequisicaoDePagina requisicao) {
		return CursoResposta.dePagina(repository.buscarPelaModalidade(modalidade, requisicao));
	}

	public Pagina<CursoResposta> buscarPelaAreaTecnologica(AreaTecnologica area, RequisicaoDePagina requisicao) {
		return CursoResposta.dePagina(repository.buscarPelaAreaTecnologica(area, requisicao));
	}

	public Pagina<CursoResposta> buscarPeloTituloContem(String titulom, RequisicaoDePagina requisicao) {
		return CursoResposta.dePagina(repository.buscarPeloTituloContem(titulom, requisicao));
	}

	public Pagina<CursoResposta> buscarPeloStatusAberto(Boolean aberto, RequisicaoDePagina requisicao) {
		return CursoResposta.dePagina(repository.buscarPeloStatusAberto(aberto, requisicao));
	}

	public Pagina<CursoResposta> buscarTodos(RequisicaoDePagina requisicao) {
		return CursoResposta.dePagina(repository.buscarTodos(requisicao));
	}
}