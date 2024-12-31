package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaTurma {

	private final TurmaRepository repository;
	private final CursoRepository cursoRepository;

	public Turma buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	public Pagina<Turma> buscarPeloCurso(String cursoID, RequisicaoDePagina requisicao) {
		Curso curso = cursoRepository.buscarPeloIDOuThrow(cursoID);
		return repository.buscarPeloCurso(curso, requisicao);
	}

	public Pagina<Turma> buscarPeloStatusAberta(Boolean aberta, RequisicaoDePagina requisicao) {
		return repository.buscarPeloStatusAberta(aberta, requisicao);
	}

	public Pagina<Turma> buscarTodas(RequisicaoDePagina requisicao){
		return repository.buscarTodas(requisicao);
	}
}
