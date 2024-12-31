package com.dougfsilva.e_AGE.dominio.curso.matricula.alunoprogresso;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface AlunoProgressoRepository {
	
	AlunoProgresso salvar(AlunoProgresso progresso);
	
	List<AlunoProgresso> salvarTodos(List<AlunoProgresso> progressos);
	
	void excluir(AlunoProgresso progresso);

	Optional<AlunoProgresso> buscarPeloID(String ID);
	
	default AlunoProgresso buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Progresso de aluno com ID %s não encontrado", ID)));
	}
	
	List<AlunoProgresso> buscarPelaMatricula(Matricula matricula);
	
	List<AlunoProgresso> buscarPeloModulo(Modulo modulo);
		
	Pagina<AlunoProgresso> buscarTodos(RequisicaoDePagina requisicao);
	
	Boolean existePeloModuloEStatus(Modulo modulo, ProgressoStatus status);
	
	Boolean existePeloModuloEMatricula(Modulo modulo, Matricula matricula);
	
	Boolean existePelaMatricula(Matricula matricula);
	
	Boolean existePeloModulo(Modulo modulo);
}
