package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import java.util.List;

import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaAreaTecnologica {

	private final AreaTecnologicaRepository repository;

	AreaTecnologica buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}

	List<AreaTecnologica> buscarPeloTituloContem(String titulo) {
		return repository.buscarPeloTituloContem(titulo);
	}

	List<AreaTecnologica> buscarTodas() {
		return repository.buscarTodas();
	}

}
