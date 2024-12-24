package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnolgica;

import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeAreaTecnologicaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaAreaTecnologica {

	private final AreaTecnologicaRepository repository;

	public void validarUnicoTitulo(String titulo) {
		if (repository.existePeloTitulo(titulo)) {
			throw new ErroDeValidacaoDeAreaTecnologicaException(
					String.format("Area tecnologica com titulo %s já existe", titulo));
		}
	}
}
