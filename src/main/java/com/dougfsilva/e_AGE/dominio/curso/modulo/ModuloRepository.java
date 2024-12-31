package com.dougfsilva.e_AGE.dominio.curso.modulo;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface ModuloRepository {

	Modulo salvar(Modulo modulo);
	
	List<Modulo> salvarTodos(List<Modulo> modulos);

	void excluir(Modulo modulo);
	
	Optional<Modulo> buscarPeloID(String ID);

	default Modulo buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Modulo com ID %s não encontrado", ID)));
	}
	
	Optional<Modulo> buscarPeloNumeroDoModulo(Integer numero);

	List<Modulo> buscarPelaTurma(Turma turma);
	
	Pagina<Modulo> buscarTodos(RequisicaoDePagina requisicao);
	
	Boolean existePelaTurma(Turma turma);
	
	Boolean existePelaTurmaECodigo(Turma turma, String codigo);
	
	Integer contarModulosPorTurma(Turma turma);
}
