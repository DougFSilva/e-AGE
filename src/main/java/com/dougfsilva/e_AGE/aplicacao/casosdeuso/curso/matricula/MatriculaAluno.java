package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.aplicacao.formulario.MatriculaAlunoForm;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.matricula.alunoprogresso.AlunoProgresso;
import com.dougfsilva.e_AGE.dominio.curso.matricula.alunoprogresso.AlunoProgressoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.alunoprogresso.ProgressoStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloStatus;
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
	private final ModuloRepository moduloRepository;
	private final AlunoRepository alunoRepository;
	private final AlunoProgressoRepository alunoProgressoRepository;
	
	public Matricula matricular(MatriculaAlunoForm form) {
		Turma turma = turmaRepository.buscarPeloIDOuThrow(form.turmaID());
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(form.alunoID());
		garantirAlunoNaoMatriculadoNaTurma(aluno, turma);
		Matricula matricula = new Matricula(form.registro(), turma, aluno, form.dataDaMatricula(), MatriculaStatus.ALUNO_MATRICULADO);
		criarAlunoProgressoParaOsModulosIniciadosDaTurma(matricula);
		return repository.salvar(matricula);
	}
	
	private void garantirAlunoNaoMatriculadoNaTurma(Aluno aluno, Turma turma) {
		if (repository.existePelaTurmaEAluno(turma, aluno)) {
			throw new ErroDeValidacaoDeMatriculaException(String.format("Aluno %s já matriculado na turma %s", aluno.getNome(), turma.getCodigo()));
		}
	}
	
	private void criarAlunoProgressoParaOsModulosIniciadosDaTurma(Matricula matricula) {
		List<Modulo> modulos = moduloRepository.buscarPelaTurma(matricula.getTurma());
		List<AlunoProgresso> progressos = modulos
				.stream()
				.filter(modulo -> modulo.getStatus() == ModuloStatus.INICIADO)
				.map(modulo -> new AlunoProgresso(matricula, modulo, ProgressoStatus.INICIADO))
				.collect(Collectors.toList());
		alunoProgressoRepository.salvarTodos(progressos);
	}
}
