package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.TurmaResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaTurma {

	private final TurmaRepository repository;
	private final CursoRepository cursoRepository;

	public TurmaResposta buscarPeloID(String ID) {
		return TurmaResposta.deTurma(repository.buscarPeloIDOuThrow(ID));
	}

	public Pagina<TurmaResposta> buscarPeloCurso(String cursoID, RequisicaoDePagina requisicao) {
		Curso curso = cursoRepository.buscarPeloIDOuThrow(cursoID);
		return TurmaResposta.dePagina(repository.buscarPeloCurso(curso, requisicao));
	}

	public Pagina<TurmaResposta> buscarPeloStatusAberta(Boolean aberta, RequisicaoDePagina requisicao) {
		return TurmaResposta.dePagina(repository.buscarPeloStatusAberta(aberta, requisicao));
	}

	public Pagina<TurmaResposta> buscarPelaDataDeAbertura(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicao) {
		return TurmaResposta.dePagina(repository.buscarPelaDataDeAbertura(dataInicial, dataFinal, requisicao));
	}

	public Pagina<TurmaResposta> buscarPelaDataDeFechamento(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicao) {
		return TurmaResposta.dePagina(repository.buscarPelaDataDeFechamento(dataInicial, dataFinal, requisicao));
	}

	public Pagina<TurmaResposta> buscarTodas(RequisicaoDePagina requisicao){
		return TurmaResposta.dePagina(repository.buscarTodas(requisicao));
	}
}
