package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReprovaAluno {

	private final MatriculaRepository repository;
	
	public MatriculaResposta reprovarPeloID(String ID) {
		Matricula matricula = repository.buscarPeloIDOuThrow(ID);
		garantirMatriculaAtivaOuAlunoAprovado(matricula);
		matricula.setStatus(MatriculaStatus.ALUNO_REPROVADO);
		return MatriculaResposta.deMatricula(repository.salvar(matricula));
	}
	
	private void garantirMatriculaAtivaOuAlunoAprovado(Matricula matricula) {
		if (matricula.getStatus() != MatriculaStatus.MATRICULA_ATIVA && matricula.getStatus() != MatriculaStatus.ALUNO_APROVADO) {
			throw new ErroDeValidacaoDeMatriculaException("Não é possível reprovar um aluno cujo status de matrícula é diferente de ativa ou aprovado");
		}
	}
}
