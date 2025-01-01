package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.reprova;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.reprova.ReprovaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEvasaoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaReprova {

	private final ReprovaRepository repository;
	
	public void validarUnidaReprovaPorMatricula(Matricula matricula) {
		if(repository.existePelaMatricula(matricula)) {
			throw new ErroDeValidacaoDeEvasaoException(String.format("Já existe reprova para essa matrícula %s", matricula.getRegistro()));
		}
	}
}
