package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiTurma {

	private final TurmaRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final ModuloRepository moduloRepository;
	
	public void excluirPeloID(String ID) {
		Turma turma = repository.buscarPeloIDOuThrow(ID);
		garantirTurmaSemMatriculas(turma);
		garantirTurmaSemModulos(turma);
		repository.excluir(turma);
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
