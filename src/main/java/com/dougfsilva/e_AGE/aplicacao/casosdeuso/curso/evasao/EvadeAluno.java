package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.evasao;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaEvasaoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.EvasaoResposta;
import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEvasaoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EvadeAluno {

	private final EvasaoRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final LogPadrao log;
	
	public EvasaoResposta evadir(CriaEvasaoForm form) {
		try {
			Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(form.matriculaID());
			garantirUnicaEvasaoPorMatricula(matricula);
			garantirMatriculaAtivaOuAlunoAprovado(matricula);
			Matricula matriculaAtualizada = atualizarStatusDaMatricula(matricula);
			Evasao evasao = new Evasao(matriculaAtualizada, form.motivo());
			Evasao evasaoSalva = repository.salvar(evasao);
			log.info(String.format("Criada evasão para aluno %s de matricula com ID %s", 
					evasaoSalva.getMatricula().getAluno().getNome(), evasaoSalva.getMatricula().getID()));
			return EvasaoResposta.deEvasao(evasaoSalva);
		} catch (ErroDeValidacaoDeMatriculaException | ErroDeValidacaoDeCamposException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao evadir aluno de matrícula com ID %s : %s", form.matriculaID(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao evadir aluno de matrícula com ID %s : %s", form.matriculaID(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		}
	}
	
	private Matricula atualizarStatusDaMatricula(Matricula matricula) {
		matricula.setStatus(MatriculaStatus.ALUNO_EVADIDO);
		return matriculaRepository.salvar(matricula);
	}
	
	private void garantirMatriculaAtivaOuAlunoAprovado(Matricula matricula) {
		if (matricula.getStatus() != MatriculaStatus.MATRICULA_ATIVA && matricula.getStatus() != MatriculaStatus.ALUNO_REPROVADO) {
			throw new ErroDeValidacaoDeMatriculaException("Não é possível evadir um aluno cujo status de matrícula é diferente de ativa ou reprovado");
		}
	}
	
	private void garantirUnicaEvasaoPorMatricula(Matricula matricula) {
		if (repository.existePelaMatricula(matricula)) {
			throw new ErroDeValidacaoDeEvasaoException(String.format("Evasão para a matrícula com ID %s já existe na base de dados", matricula.getID()));
		}
	}
}
