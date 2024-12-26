package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCursoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaCurso {

	private final CursoRepository repository;
	
	public void validarUnicoTitulo(String titulo) {
		if(repository.existePeloTitulo(titulo)) {
			throw new ErroDeValidacaoDeCursoException(String.format("Titulo de curso %s já existe na base de dados", titulo));
		}
	}
}
