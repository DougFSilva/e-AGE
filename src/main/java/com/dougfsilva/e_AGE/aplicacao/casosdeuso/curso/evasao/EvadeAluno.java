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
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEvasaoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EvadeAluno {

	private final EvasaoRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final ModuloRepository moduloRepository;

	public Evasao evadir(EvadeAlunoForm form) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(form.matriculaID());
		validarMatricula(matricula);
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
		validarModulo(modulo, matricula.getTurma());
		Evasao evasao = new Evasao(matricula, modulo, form.data(), form.motivo());
		matricula.setStatus(MatriculaStatus.ALUNO_EVADIDO);
		matriculaRepository.salvar(matricula);
		return repository.salvar(evasao);
	}
	
	private void validarMatricula(Matricula matricula) {
		garantirUnicaEvasaoPorMatricula(matricula);
		garantirStatusAlunoMatriculado(matricula);
	}
	
	private void validarModulo(Modulo modulo, Turma turma) {
		garantirModuloEmAndamentoOuConcluído(modulo);
		garantirModuloPertencenteATurma(modulo, turma);
	}
	
	private void garantirUnicaEvasaoPorMatricula(Matricula matricula) {
		if(repository.existePelaMatricula(matricula)) {
			throw new ErroDeValidacaoDeEvasaoException(String.format("Já existe evasão para matrícula %s", matricula.getRegistro()));
		}
	}

	private void garantirStatusAlunoMatriculado(Matricula matricula) {
		if (matricula.getStatus() != MatriculaStatus.ALUNO_MATRICULADO) {
			throw new ErroDeValidacaoDeEvasaoException("Não é possível evadir um aluno com status diferente de ALUNO_MATRICULADO");
		}
	}

	private void garantirModuloEmAndamentoOuConcluído(Modulo modulo) {
		if (modulo.getStatus() == ModuloStatus.NAO_INICIADO) {
			throw new ErroDeValidacaoDeEvasaoException(String.format(
					"Não é possível evadir o aluno pois o módulo %s ainda não foi iniciado", modulo.getCodigo()));
		}
	}

	private void garantirModuloPertencenteATurma(Modulo modulo, Turma turma) {
		if (!modulo.getTurma().equals(turma)) {
			throw new ErroDeValidacaoDeEvasaoException(
					String.format("Não é possível evadir o aluno pois o módulo %s não pertence a turma %s",
							modulo.getCodigo(), turma.getCodigo()));
		}
	}
}
