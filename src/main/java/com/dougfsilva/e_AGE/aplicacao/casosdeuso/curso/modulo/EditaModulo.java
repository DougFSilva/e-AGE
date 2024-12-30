package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.aplicacao.dto.EditaModuloForm;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaModulo {

	private final ModuloRepository repository;
	private final TurmaRepository turmaRepository;
	private final ValidaModulo validador;

	public Modulo editar(EditaModuloForm form) {
		Modulo modulo = repository.buscarPeloIDOuThrow(form.ID());
		Modulo moduloEditado = editarDados(form, modulo);
		return repository.salvar(moduloEditado);

	}

	private Modulo editarDados(EditaModuloForm form, Modulo modulo) {
		if (form.turmaID() != null && !form.turmaID().isBlank()
				&& !form.turmaID().equalsIgnoreCase(modulo.getTurma().getID())) {
			Turma turma = turmaRepository.buscarPeloIDOuThrow(form.turmaID());
			modulo.setTurma(turma);
		}
		if (form.codigo() != null && !form.codigo().isBlank()) {
			validador.validarUnicoCodigoPorTurma(modulo.getTurma(), form.codigo());
			modulo.setCodigo(form.codigo());
		}
		return modulo;
	}
}
