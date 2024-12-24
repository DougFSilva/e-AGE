package com.dougfsilva.e_AGE.dominio.curso.areatecnologica;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

public interface AreaTecnologicaRepository {

	AreaTecnologica salvar(AreaTecnologica area);

	void excluir(AreaTecnologica area);

	Optional<AreaTecnologica> buscarPeloID(String ID);

	default AreaTecnologica buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Area tecnologica com ID %s não encontrada", ID)));
	}
	
	List<AreaTecnologica> buscarPeloTituloContem(String titulo);
	
	List<AreaTecnologica> buscarTodas();
	
	Boolean existePeloTitulo(String titulo);
	
}
