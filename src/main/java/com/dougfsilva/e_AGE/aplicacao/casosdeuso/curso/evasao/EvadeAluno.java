package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.evasao;

import com.dougfsilva.e_AGE.aplicacao.formulario.EvadeAlunoForm;
import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEvasaoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EvadeAluno {

	private final EvasaoRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final ModuloRepository moduloRepository;

	public Evasao evadir(EvadeAlunoForm form) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(form.matriculaID());
		garantirAlunoMatriculado(matricula);
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
		garantirModuloPertenceATurma(modulo, matricula);
		garantirModuloEmAndamentoOuConcluído(modulo);
		Evasao evasao = new Evasao(matricula.getAluno(), modulo, form.data(), form.motivo());
		matricula.setStatus(MatriculaStatus.ALUNO_EVADIDO);
		matriculaRepository.salvar(matricula);
		return repository.salvar(evasao);
	}

	private void garantirAlunoMatriculado(Matricula matricula) {
		switch (matricula.getStatus()) {
		case ALUNO_REPROVADO: {
			throw new ErroDeValidacaoDeEvasaoException("Não é possível evadir um aluno que esteja reprovado");
		}
		case ALUNO_EVADIDO: {
			throw new ErroDeValidacaoDeEvasaoException("Não é possível evadir um aluno que já esteja evadido");
		}
		case ALUNO_CERTIFICADO: {
			throw new ErroDeValidacaoDeEvasaoException("Não é possível evadir um aluno que já esteja certificado");
		}
		default:
			break;
		}
	}

	private void garantirModuloEmAndamentoOuConcluído(Modulo modulo) {
		if (modulo.getStatus() == ModuloStatus.NAO_INICIADO) {
			throw new ErroDeValidacaoDeEvasaoException(String.format(
					"Não é possível evadir o aluno pois o módulo %s ainda não foi iniciado", modulo.getCodigo()));
		}
	}

	private void garantirModuloPertenceATurma(Modulo modulo, Matricula matricula) {
		if (!modulo.getTurma().equals(matricula.getTurma())) {
			throw new ErroDeValidacaoDeEvasaoException(
					String.format("Não é possível evadir o aluno pois o módulo %s não pertence a turma %s",
							modulo.getCodigo(), matricula.getTurma().getCodigo()));
		}
	}
}
