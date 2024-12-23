package com.dougfsilva.e_AGE.dominio.matricula;

import java.time.LocalDate;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface MatriculaRepository {

	Matricula salvar(Matricula matricula);

	void remover(Matricula matricula);

	Optional<Matricula> buscarPeloID(String ID);

	default Matricula buscarPeloIDOuExcecao(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Matricula com ID %s não encontrada", ID)));
	}
	
	Pagina<Matricula> buscarTodasPeloCurso(Curso curso, RequisicaoDePagina requisicao);
	
	Pagina<Matricula> buscarTodasPelaTurma(Turma turma, RequisicaoDePagina requisicao);
	
	Pagina<Matricula> buscarTodasPeloModulo(Modulo modulo, RequisicaoDePagina requisicao);
	
	Pagina<Matricula> buscarTodasPeloStatus(MatriculaStatus status, RequisicaoDePagina requisicao);
	
	Pagina<Matricula> buscarTodasPelaDataDaMatricula(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicao);
	
	Pagina<Matricula> buscarTodas(RequisicaoDePagina requisicao);
}
