package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiMatricula {

	private final MatriculaRepository repository;
	private final EvasaoRepository evasaoRepository;
	private final LogPadrao log;

	public void excluirPeloID(String ID) {
		try {
			Matricula matricula = repository.buscarPeloIDOuThrow(ID);
			garantirEvasaoSemMatriculas(matricula);
			repository.excluir(matricula);
			log.info(String.format("Excluída matrícula do aluno %s no módulo %s", matricula.getAluno().getNome(), matricula.getModulo().getCodigo()));
		} catch (ObjetoNaoEncontradoException | ErroDeEntidadeComVinculosException e) {
			String mensagem = String.format("Erro ao excluir matrícula com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir matrícula com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		}
	}

	private void garantirEvasaoSemMatriculas(Matricula matricula) {
		if (evasaoRepository.existePelaMatricula(matricula)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir a matrícula porque existem evasões associadas a ela");
		}
	}
}
