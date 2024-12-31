package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeTurmaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FechaTurma {

	private final TurmaRepository repository;
	private final MatriculaRepository matriculaRepository;
	
	public Turma fecharPeloID(String ID) {
		Turma turma = repository.buscarPeloIDOuThrow(ID);
		garantirNenhumaMatriculaNaTurma(turma);
		turma.setAberta(false);
		return repository.salvar(turma);
	}
	
	private void garantirNenhumaMatriculaNaTurma(Turma turma) {
		if (matriculaRepository.existePelaTurmaEStatus(turma, MatriculaStatus.ALUNO_MATRICULADO)) {
			throw new ErroDeValidacaoDeTurmaException("Não é possível fechar a turma pois ainda há alunos matriculados");
		}
	}
}
