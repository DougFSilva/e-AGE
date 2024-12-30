package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaMatricula {

	private final MatriculaRepository repository;
	private final CursoRepository cursoRepository;
	private final TurmaRepository turmaRepository;
	private final ModuloRepository moduloRepository;

	public Matricula buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	public Pagina<Matricula> buscarPeloIDdoCurso(String cursoID, RequisicaoDePagina requisicao){
		Curso curso = cursoRepository.buscarPeloIDOuThrow(cursoID);
		return repository.buscarPeloCurso(curso, requisicao);
	}

	public Pagina<Matricula> buscarPeloIDdaTurma(String turmaID, RequisicaoDePagina requisicao){
		Turma turma = turmaRepository.buscarPeloIDOuThrow(turmaID);
		return repository.buscarPelaTurma(turma, requisicao);
	}

	public Pagina<Matricula> buscarPeloIDdoModulo(String moduloID, RequisicaoDePagina requisicao){
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(moduloID);
		return repository.buscarPeloModulo(modulo, requisicao);
	}

	public Pagina<Matricula> buscarPeloStatus(MatriculaStatus status, RequisicaoDePagina requisicao){
		return repository.buscarPeloStatus(status, requisicao);
	}

	public Pagina<Matricula> buscarPelaDataDaMatricula(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicao){
		return repository.buscarPelaDataDaMatricula(dataInicial, dataFinal, requisicao);
	}

	public Pagina<Matricula> buscarTodas(RequisicaoDePagina requisicao){
		return repository.buscarTodas(requisicao);
	}
}
