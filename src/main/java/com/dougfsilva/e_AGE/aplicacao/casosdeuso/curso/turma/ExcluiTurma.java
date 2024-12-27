package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComImagemException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComTurmaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiTurma {

	private final TurmaRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final ModuloRepository moduloRepository;
	private final LogPadrao log;
	
	public void excluirPeloID(String ID) {
		try {
			Turma turma = repository.buscarPeloIDOuThrow(ID);
			garantirTurmaSemMatriculas(turma);
			garantirTurmaSemModulos(turma);
			repository.excluir(turma);
			log.info(String.format("Excluída turma %s", turma.getCodigo()));
		} catch (ObjetoNaoEncontradoException | ErroDeOperacaoComImagemException | ErroDeEntidadeComVinculosException e) {
			String mensagem = String.format("Erro ao excluir turma com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComTurmaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir turma com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComTurmaException(mensagem, e);
		}
	}
	
	private void garantirTurmaSemMatriculas(Turma turma) {
		if (matriculaRepository.existePelaTurma(turma)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir a turma porque existem matriculas associadas a ela");
		}
	}
	
	private void garantirTurmaSemModulos(Turma turma) {
		if (moduloRepository.existePelaTurma(turma)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir a turma porque existem módulos associados a ela");
		}
	}
}
