package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeAreaTecnologicaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaAreaTecnologica {

	private final AreaTecnologicaRepository repository;

	public void validarUnicoTitulo(String titulo) {
		if (repository.existePeloTitulo(titulo)) {
			throw new ErroDeValidacaoDeAreaTecnologicaException(
					String.format("Titulo %s já existe na base dados", titulo));
		}
	}
}
