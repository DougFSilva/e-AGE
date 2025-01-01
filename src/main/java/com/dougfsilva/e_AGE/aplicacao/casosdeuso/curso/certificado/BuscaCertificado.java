package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.certificado;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.certificado.Certificado;
import com.dougfsilva.e_AGE.dominio.curso.certificado.CertificadoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaCertificado {

	private final CertificadoRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final CursoRepository cursoRepository;
	private final TurmaRepository turmaRepository;
	private final AlunoRepository alunoRepository;

	public Certificado buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	public Certificado buscaPelaMatricula(String matriculaID) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(matriculaID);
		return repository.buscarPelaMatricula(matricula).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Certificado de aluno com matrícula %s não encontrado", matricula.getRegistro())));
	}

	public List<Certificado> buscarPeloAluno(String alunoID) {
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(alunoID);
		return repository.buscarPeloAluno(aluno);
	}

	public Pagina<Certificado> buscarPeloCurso(String cursoID, RequisicaoDePagina requisicao) {
		Curso curso = cursoRepository.buscarPeloIDOuThrow(cursoID);
		return repository.buscarPeloCurso(curso, requisicao);
	}

	public List<Certificado> buscarPelaTurma(String turmaID) {
		Turma turma = turmaRepository.buscarPeloIDOuThrow(turmaID);
		return repository.buscarPelaTurma(turma);
	}

	public Pagina<Certificado> buscarPelaDataDeCertificacao(LocalDate dataInicial, LocalDate dataFinal,
			RequisicaoDePagina requisicao) {
		return repository.buscarPelaDataDeCertificacao(dataInicial, dataFinal, requisicao);
	}

	public Pagina<Certificado> buscarTodos(RequisicaoDePagina requisicao) {
		return repository.buscarTodos(requisicao);
	}
}
