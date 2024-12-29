package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.evasao;

import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaEvasaoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.EvasaoResposta;
import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEvasaoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EvadeAluno {

	private final EvasaoRepository repository;
	private final MatriculaRepository matriculaRepository;
	
	public EvasaoResposta evadir(CriaEvasaoForm form) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(form.matriculaID());
		garantirUnicaEvasaoPorMatricula(matricula);
		garantirMatriculaAtivaOuAlunoAprovado(matricula);
		Matricula matriculaAtualizada = atualizarStatusDaMatricula(matricula);
		Evasao evasao = new Evasao(matriculaAtualizada, form.motivo());
		Evasao evasaoSalva = repository.salvar(evasao);
		return EvasaoResposta.deEvasao(evasaoSalva);
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
