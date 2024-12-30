package com.dougfsilva.e_AGE.dominio.curso.matricula;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface MatriculaRepository {

	Matricula salvar(Matricula matricula);
	
	List<Matricula> salvarTodas(List<Matricula> matriculas);

	void excluir(Matricula matricula);

	Optional<Matricula> buscarPeloID(String ID);

	default Matricula buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Matricula com ID %s não encontrada", ID)));
	}
	
	Pagina<Matricula> buscarPeloCurso(Curso curso, RequisicaoDePagina requisicao);
	
	Pagina<Matricula> buscarPelaTurma(Turma turma, RequisicaoDePagina requisicao);
	
	List<Matricula> buscarPelaTurmaEAluno(Turma turma, Aluno aluno);
	
	Pagina<Matricula> buscarPeloModulo(Modulo modulo, RequisicaoDePagina requisicao);
	
	List<Matricula> buscarPeloModulo(Modulo modulo);
	
	List<Matricula> buscarPeloAluno(Aluno aluno);
	
	Pagina<Matricula> buscarPeloStatus(MatriculaStatus status, RequisicaoDePagina requisicao);
	
	Pagina<Matricula> buscarPelaDataDaMatricula(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicao);
	
	Pagina<Matricula> buscarTodas(RequisicaoDePagina requisicao);
	
	Boolean existePelaTurma(Turma turma);
	
	Boolean existePeloAluno(Aluno aluno);
	
	Boolean existePeloModuloEAluno(Modulo modulo, Aluno aluno);
	
	Boolean existePeloRegistro(String registro);
}
