package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AprovarAluno {

	private final MatriculaRepository repository;
	private final LogPadrao log;
	
	public MatriculaResposta aprovarPeloID(String ID) {
		try {
			Matricula matricula = repository.buscarPeloIDOuThrow(ID);
			matricula.setStatus(MatriculaStatus.ALUNO_APROVADO);
			Matricula matriculaSalva = repository.salvar(matricula);
			log.info(String.format("Aluno %s aprovado no módulo %s", matriculaSalva.getAluno().getNome(), matriculaSalva.getModulo().getCodigo()));
			return MatriculaResposta.deMatricula(matriculaSalva);
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao aprovar aluno da materícula com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado aprovar aluno  da matrícula com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		}
	}
}
