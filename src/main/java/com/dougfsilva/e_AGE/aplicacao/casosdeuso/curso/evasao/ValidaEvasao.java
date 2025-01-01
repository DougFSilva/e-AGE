package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.evasao;

import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEvasaoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaEvasao {

	private final EvasaoRepository repository;
	
	public void validarUnicaEvasaoPorMatricula(Matricula matricula) {
		if(repository.existePelaMatricula(matricula)) {
			throw new ErroDeValidacaoDeEvasaoException(String.format("Já existe evasão para essa matrícula %s", matricula.getRegistro()));
		}
	}
	
}
