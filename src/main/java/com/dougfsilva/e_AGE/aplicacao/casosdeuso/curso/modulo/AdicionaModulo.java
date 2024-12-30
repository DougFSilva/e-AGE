package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import java.util.List;

import com.dougfsilva.e_AGE.aplicacao.dto.CriaModuloForm;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
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
		Modulo modulo = new Modulo(form.codigo(), turma, form.dataDeAbertura());
		List<Modulo> modulos = repository.buscarPelaTurma(turma);
		modulos.forEach(m -> m.setModuloFinal(false));
		modulo.setNumeroDoModulo(modulos.size() + 1);
		modulo.setModuloFinal(true);
		modulos.add(modulo);
		repository.salvarTodos(modulos);
		return modulo;
	}
}
