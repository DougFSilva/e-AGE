package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaMatricula {

	private final MatriculaRepository repository;
	
	public void validarUnicoRegistro(String registro) {
		if (repository.existePeloRegistro(registro)) {
			throw new ErroDeValidacaoDeMatriculaException("Registro %s já existe na base de dados");
		}
	}
}
