package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.reprova;

import com.dougfsilva.e_AGE.aplicacao.formulario.ReprovaAlunoForm;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloStatus;
import com.dougfsilva.e_AGE.dominio.curso.reprova.Reprova;
import com.dougfsilva.e_AGE.dominio.curso.reprova.ReprovaRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeReprovaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReprovaAluno {

	private final ReprovaRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final ModuloRepository moduloRepository;
	
	public Reprova reprovar(ReprovaAlunoForm form) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(form.matriculaID());
		validarMatricula(matricula);
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
		validarModulo(modulo, matricula.getTurma());
		matricula.setStatus(MatriculaStatus.ALUNO_REPROVADO);
		Reprova reprova = new Reprova(matricula, modulo, form.data(), form.causa(), form.detalhes());
		return repository.salvar(reprova);
	}
	
	private void validarMatricula(Matricula matricula) {
		garantirUnicaReprovaPorMatricula(matricula);
		garantirStatusAlunoMatriculado(matricula);
	}
	
	private void validarModulo(Modulo modulo, Turma turma) {
		garantirModuloEmAndamentoOuConcluído(modulo);
		garantirModuloPertencenteATurma(modulo, turma);
	}
	
	private void garantirUnicaReprovaPorMatricula(Matricula matricula) {
		if(repository.existePelaMatricula(matricula)) {
			throw new ErroDeValidacaoDeReprovaException(String.format("Já existe reprova para matrícula %s", matricula.getRegistro()));
		}
	}
	
	private void garantirStatusAlunoMatriculado(Matricula matricula) {
		if (matricula.getStatus() != MatriculaStatus.ALUNO_MATRICULADO) {
			throw new ErroDeValidacaoDeReprovaException("Não é possível reprovar um aluno com status diferente de ALUNO_MATRICULADO");
		}
	}
	
	private void garantirModuloEmAndamentoOuConcluído(Modulo modulo) {
		if (modulo.getStatus() == ModuloStatus.NAO_INICIADO) {
			throw new ErroDeValidacaoDeReprovaException(String.format(
					"Não é possível reprovar o aluno pois o módulo %s ainda não foi iniciado", modulo.getCodigo()));
		}
	}

	private void garantirModuloPertencenteATurma(Modulo modulo, Turma turma) {
		if (!modulo.getTurma().equals(turma)) {
			throw new ErroDeValidacaoDeReprovaException(
					String.format("Não é possível reporvar o aluno pois o módulo %s não pertence a turma %s",
							modulo.getCodigo(), turma.getCodigo()));
		}
	} 
}
	