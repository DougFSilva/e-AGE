package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.util.Optional;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReativarMatriculaDeAluno {

	private final MatriculaRepository repository;
	private final EvasaoRepository evasaoRepository;
	private final LogPadrao log;
	
	public MatriculaResposta reativarPeloID(String ID) {
		try {
			Matricula matricula = repository.buscarPeloIDOuThrow(ID);
			excluirEvasao(matricula);
			matricula.setStatus(MatriculaStatus.MATRICULA_ATIVA);
			Matricula matriculaSalva = repository.salvar(matricula);
			log.info(String.format("Reativada matrícula de aluno %s no módulo %s", matriculaSalva.getAluno().getNome(), matriculaSalva.getModulo().getCodigo()));
			return MatriculaResposta.deMatricula(matriculaSalva);
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao reativar matricula com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao reativar matrícula com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		}
	}
	
	private void excluirEvasao(Matricula matricula) {
		if (matricula.getStatus() == MatriculaStatus.ALUNO_EVADIDO) {
			Optional<Evasao> evasao = evasaoRepository.buscarPelaMatricula(matricula);
			if (evasao.isPresent()) {
				evasaoRepository.excluir(evasao.get());
				log.info(String.format("Excluída evasão com ID %s", evasao.get().getID()));
			}
		}
	}
}
