package com.dougfsilva.e_AGE.dominio.curso.evasao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface EvasaoRepository {

	Evasao salvar(Evasao evasao);

	void excluir(Evasao evasao);

	Optional<Evasao> buscarPeloID(String ID);

	default Evasao buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Evasao com ID %s não encontrada", ID)));
	}
	
	Optional<Evasao> buscarPelaMatricula(Matricula matricula);
	
	List<Evasao> buscarPorModulo(Modulo modulo);
	
	Pagina<Evasao> buscarPeloCurso(Curso curso, RequisicaoDePagina requisicao);
	
	List<Evasao> buscarPelaTurma(Turma turma);

	List<Evasao> buscarPeloAluno(Aluno aluno);
	
	Pagina<Evasao> buscarPelaData(LocalDate min, LocalDate max, RequisicaoDePagina requisicao);

	Pagina<Evasao> buscarTodas(RequisicaoDePagina requisicao);
	
	Boolean existePelaMatricula(Matricula matricula);

}
