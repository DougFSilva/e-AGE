package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.aplicacao.formulario.EditaMatriculaForm;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaMatricula {

	private final MatriculaRepository repository;
	private final ValidaMatricula validador;
	
	public Matricula editar(EditaMatriculaForm form) {
		Matricula matricula = editarDados(form);
		return repository.salvar(matricula);
	}
	
	private Matricula editarDados(EditaMatriculaForm form) {
		Matricula matricula = repository.buscarPeloIDOuThrow(form.ID());
		if (form.registro() != null && !form.registro().isBlank() && !form.registro().equalsIgnoreCase(matricula.getRegistro())) {
			validador.validarUnicoRegistro(form.registro());
			matricula.setRegistro(form.registro());
		}
		if (form.dataDaMatricula() != null) {
			matricula.setDataDaMatricula(form.dataDaMatricula());
		}
		return matricula;
	}
	
}
