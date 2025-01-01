package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.aplicacao.formulario.EditaModuloForm;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaModulo {

	private final ModuloRepository repository;
	private final ValidaModulo validador;

	public Modulo editar(EditaModuloForm form) {
		Modulo modulo = editarDados(form);
		return repository.salvar(modulo);

	}

	private Modulo editarDados(EditaModuloForm form) {
		Modulo modulo = repository.buscarPeloIDOuThrow(form.ID());

		if (form.codigo() != null && !form.codigo().isBlank()) {
			validador.validarUnicoCodigoPorTurma(modulo.getTurma(), form.codigo());
			modulo.setCodigo(form.codigo());
		}
		return modulo;
	}
}
