package com.dougfsilva.e_AGE.dominio.curso.turma;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface TurmaRepository {

	Turma salvar(Turma turma);

	void excluir(Turma turma);

	Optional<Turma> buscarPeloID(String ID);

	default Turma buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Turma com ID %s não encontrada", ID)));
	}

	Pagina<Turma> buscarPeloCurso(Curso curso, RequisicaoDePagina requisicao);

	Pagina<Turma> buscarPeloStatusAberta(Boolean aberta, RequisicaoDePagina requisicao);

	Pagina<Turma> buscarTodas(RequisicaoDePagina requisicao);
	
	Boolean existePeloCursoECodigo(Curso curso, String codigo);
	
	Boolean existePeloCurso(Curso curso);
	
	Boolean existePeloCursoEStatusAberta(Curso curso, Boolean aberta);
}
