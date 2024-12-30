package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.Modalidade;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaCurso {

	private final CursoRepository repository;
	private final AreaTecnologicaRepository areaTecnologicaRepository;

	public Curso buscaPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	public Pagina<Curso> buscarPelaModalidade(Modalidade modalidade, RequisicaoDePagina requisicao) {
		return repository.buscarPelaModalidade(modalidade, requisicao);
	}

	public Pagina<Curso> buscarPeloIDdaAreaTecnologica(String areaID, RequisicaoDePagina requisicao) {
		AreaTecnologica area = areaTecnologicaRepository.buscarPeloIDOuThrow(areaID);
		return repository.buscarPelaAreaTecnologica(area, requisicao);
	}

	public Pagina<Curso> buscarPeloTituloContem(String titulo, RequisicaoDePagina requisicao) {
		return repository.buscarPeloTituloContem(titulo, requisicao);
	}

	public Pagina<Curso> buscarPeloStatusAberto(Boolean aberto, RequisicaoDePagina requisicao) {
		return repository.buscarPeloStatusAberto(aberto, requisicao);
	}

	public Pagina<Curso> buscarTodos(RequisicaoDePagina requisicao) {
		return repository.buscarTodos(requisicao);
	}
}
