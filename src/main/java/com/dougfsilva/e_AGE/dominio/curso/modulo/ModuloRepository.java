package com.dougfsilva.e_AGE.dominio.curso.modulo;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface ModuloRepository {

	Modulo salvar(Modulo modulo);

	void excluir(Modulo modulo);

	Optional<Modulo> buscarPeloID(String ID);

	default Modulo buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Modulo com ID %s não encontrado", ID)));
	}

	Pagina<Modulo> buscarPeloCurso(Curso curso, RequisicaoDePagina requisicao);

	Pagina<Turma> buscarTodos(RequisicaoDePagina requisicao);
}
