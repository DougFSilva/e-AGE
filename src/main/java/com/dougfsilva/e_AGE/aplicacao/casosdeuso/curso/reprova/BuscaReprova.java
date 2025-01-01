package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.reprova;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.reprova.CausaReprova;
import com.dougfsilva.e_AGE.dominio.curso.reprova.Reprova;
import com.dougfsilva.e_AGE.dominio.curso.reprova.ReprovaRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaReprova {

	private final ReprovaRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final ModuloRepository moduloRepository;
	private final CursoRepository cursoRepository;
	private final TurmaRepository turmaRepository;
	private final AlunoRepository alunoRepository;

	public Reprova buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	public Reprova buscarPelaMatricula(String matriculaID) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(matriculaID);
		return repository.buscarPelaMatricula(matricula).orElseThrow(() -> 
		new ObjetoNaoEncontradoException(String.format("Reprova de aluno com matrícula %s não encontrada", matricula.getRegistro())));
	}

	public Pagina<Reprova> buscarPeloCurso(String cursoID) {
		Curso curso = cursoRepository.buscarPeloIDOuThrow(cursoID);
		return repository.buscarPeloCurso(curso);
	}

	public List<Reprova> buscarPelaTurma(String turmaID) {
		Turma turma = turmaRepository.buscarPeloIDOuThrow(turmaID);
		return repository.buscarPelaTurma(turma);
	}

	public List<Reprova> buscarPeloAluno(String alunoID) {
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(alunoID);
		return repository.buscarPeloAluno(aluno);
	}

	public List<Reprova> buscarPeloModulo(String moduloID) {
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(moduloID);
		return repository.buscarPeloModulo(modulo);
	}

	public Pagina<Reprova> buscarPelaData(LocalDate min, LocalDate max, RequisicaoDePagina requisicao) {
		return repository.buscarPelaData(min, max, requisicao);
	}

	public Pagina<Reprova> buscarPelaCausa(CausaReprova causa, RequisicaoDePagina requisicao) {
		return repository.buscarPelaCausa(causa, requisicao);
	}

	public Pagina<Reprova> buscarTodas(RequisicaoDePagina requisicao) {
		return repository.buscarTodas(requisicao);
	}
}
