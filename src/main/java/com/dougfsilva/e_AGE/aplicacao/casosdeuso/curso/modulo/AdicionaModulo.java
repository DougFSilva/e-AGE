package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.aplicacao.formulario.CriaModuloForm;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloStatus;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdicionaModulo {

	private final ModuloRepository repository;
	private final TurmaRepository turmaRepository;
	private final ValidaModulo validador;

	public Modulo criar(CriaModuloForm form) {
		Turma turma = turmaRepository.buscarPeloIDOuThrow(form.turmaID());
		validador.validarUnicoCodigoPorTurma(turma, form.codigo());
		Long contagemDeTurmas = repository.contarPelaTurma(turma);
		Modulo modulo = new Modulo(form.codigo(), turma,  contagemDeTurmas.intValue() + 1, ModuloStatus.NAO_INICIADO);
		return repository.salvar(modulo);
	}
}
