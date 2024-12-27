package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaMatricula {

	private final MatriculaRepository repository;

	public MatriculaResposta buscarPeloID(String ID) {
		return MatriculaResposta.deMatricula(repository.buscarPeloIDOuThrow(ID));
	}

	public Pagina<MatriculaResposta> buscarPeloCurso(Curso curso, RequisicaoDePagina requisicao){
		return MatriculaResposta.dePagina(repository.buscarPeloCurso(curso, requisicao));
	}

	public Pagina<MatriculaResposta> buscarPelaTurma(Turma turma, RequisicaoDePagina requisicao){
		return MatriculaResposta.dePagina(repository.buscarPelaTurma(turma, requisicao));
	}

	public Pagina<MatriculaResposta> buscarPeloModulo(Modulo modulo, RequisicaoDePagina requisicao){
		return MatriculaResposta.dePagina(repository.buscarPeloModulo(modulo, requisicao));
	}

	public Pagina<MatriculaResposta> buscarPeloStatus(MatriculaStatus status, RequisicaoDePagina requisicao){
		return MatriculaResposta.dePagina(repository.buscarPeloStatus(status, requisicao));
	}

	public Pagina<MatriculaResposta> buscarPelaDataDaMatricula(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicao){
		return MatriculaResposta.dePagina(repository.buscarPelaDataDaMatricula(dataInicial, dataFinal, requisicao));
	}

	public Pagina<MatriculaResposta> buscarTodas(RequisicaoDePagina requisicao){
		return MatriculaResposta.dePagina(repository.buscarTodas(requisicao));
	}
}
