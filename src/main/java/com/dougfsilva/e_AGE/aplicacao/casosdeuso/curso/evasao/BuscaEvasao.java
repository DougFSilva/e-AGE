package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.evasao;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaEvasao {

	private final EvasaoRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final ModuloRepository moduloRepository;
	private final CursoRepository cursoRepository;
	private final TurmaRepository turmaRepository;
	private final AlunoRepository alunoRepository;

	public Evasao buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	public Evasao buscarPelaMatricula(String matriculaID) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(matriculaID);
		return repository.buscarPelaMatricula(matricula).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Evasão de aluno com matrícula %s não encontrada", matricula.getRegistro())));
	}

	public List<Evasao> buscarPorModulo(String moduloID) {
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(moduloID);
		return repository.buscarPorModulo(modulo);
	}

	public Pagina<Evasao> buscarPeloCurso(String cursoID, RequisicaoDePagina requisicao) {
		Curso curso = cursoRepository.buscarPeloIDOuThrow(cursoID);
		return repository.buscarPeloCurso(curso, requisicao);
	}

	public List<Evasao> buscarPelaTurma(String turmaID) {
		 Turma turma = turmaRepository.buscarPeloIDOuThrow(turmaID);
		 return repository.buscarPelaTurma(turma);
	}

	public List<Evasao> buscarPeloAluno(String alunoID) {
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(alunoID);
		return repository.buscarPeloAluno(aluno);
	}

	public Pagina<Evasao> buscarPelaData(LocalDate min, LocalDate max, RequisicaoDePagina requisicao) {
		return repository.buscarPelaData(min, max, requisicao);
	}

	public Pagina<Evasao> buscarTodas(RequisicaoDePagina requisicao) {
		return repository.buscarTodas(requisicao);
	}
}
