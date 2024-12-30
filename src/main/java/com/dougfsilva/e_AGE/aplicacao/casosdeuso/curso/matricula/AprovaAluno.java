package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AprovaAluno {

	private final MatriculaRepository repository;
	
	public Matricula aprovarPeloID(String ID) {
		Matricula matricula = repository.buscarPeloIDOuThrow(ID);
		garantirMatriculaAtivaOuAlunoReprovado(matricula);
		matricula.setStatus(MatriculaStatus.ALUNO_APROVADO);
		return repository.salvar(matricula);
	}
	
	private void garantirMatriculaAtivaOuAlunoReprovado(Matricula matricula) {
		if (matricula.getStatus() != MatriculaStatus.MATRICULA_ATIVA && matricula.getStatus() != MatriculaStatus.ALUNO_REPROVADO) {
			throw new ErroDeValidacaoDeMatriculaException("Não é possível aprovar um aluno cujo status de matrícula é diferente de ativa ou reprovado");
		}
	}
}
