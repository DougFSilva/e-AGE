package com.dougfsilva.e_AGE.dominio.curso.turma;

import java.time.LocalDate;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface TurmaRepository {

	Turma salvar(Turma turma);

	void remover(Turma turma);

	Optional<Turma> buscarPeloID(String ID);

	default Turma buscarPeloIDOuExcecao(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Turma com ID %s não encontrada", ID)));
	}

	Pagina<Turma> buscarTodasPeloCurso(Curso curso, RequisicaoDePagina requisicao);

	Pagina<Turma> buscarTodasPeloStatusAberta(Boolean aberta, RequisicaoDePagina requisicao);

	Pagina<Turma> buscarTodasPelaDataDeAbertura(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicao);

	Pagina<Turma> buscarTodasPelaDataDeFechamento(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicao);

	Pagina<Turma> buscarTodas(RequisicaoDePagina requisicao);
}
