package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReprovaAluno {

	private final MatriculaRepository repository;
	private final LogPadrao log;
	
	public MatriculaResposta reprovarPeloID(String ID) {
		try {
			Matricula matricula = repository.buscarPeloIDOuThrow(ID);
			garantirMatriculaAtivaOuAlunoAprovado(matricula);
			matricula.setStatus(MatriculaStatus.ALUNO_REPROVADO);
			Matricula matriculaSalva = repository.salvar(matricula);
			log.info(String.format("Aluno %s reprovado no módulo %s", matriculaSalva.getAluno().getNome(), matriculaSalva.getModulo().getCodigo()));
			return MatriculaResposta.deMatricula(matriculaSalva);
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao reprovar aluno da matrícula com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado reprovar aluno da matrícula com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		}
	}
	
	private void garantirMatriculaAtivaOuAlunoAprovado(Matricula matricula) {
		if (matricula.getStatus() != MatriculaStatus.MATRICULA_ATIVA && matricula.getStatus() != MatriculaStatus.ALUNO_APROVADO) {
			throw new ErroDeValidacaoDeMatriculaException("Não é possível reprovar um aluno cujo status de matrícula é diferente de ativa ou aprovado");
		}
	}
}
