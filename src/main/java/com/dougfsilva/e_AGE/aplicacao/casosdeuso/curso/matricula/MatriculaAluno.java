package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.aplicacao.formulario.MatriculaAlunoForm;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatriculaAluno {

	private final MatriculaRepository repository;
	private final TurmaRepository turmaRepository;
	private final AlunoRepository alunoRepository;
	private final ValidaMatricula validador;

	
	public Matricula matricular(MatriculaAlunoForm form) {
		validador.validarUnicoRegistro(form.registro());
		Turma turma = turmaRepository.buscarPeloIDOuThrow(form.turmaID());
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(form.alunoID());
		garantirAlunoNaoMatriculadoNaTurma(aluno, turma);
		Matricula matricula = new Matricula(form.registro(), turma, aluno, form.dataDaMatricula(), MatriculaStatus.ALUNO_MATRICULADO);
		return repository.salvar(matricula);
	}
	
	private void garantirAlunoNaoMatriculadoNaTurma(Aluno aluno, Turma turma) {
		if (repository.existePelaTurmaEAluno(turma, aluno)) {
			throw new ErroDeValidacaoDeMatriculaException(String.format("Aluno %s já matriculado na turma %s", aluno.getNome(), turma.getCodigo()));
		}
	}
}
