package com.dougfsilva.e_AGE.dominio.curso.areatecnologica;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

public interface AreaTecnologicaRepository {

	AreaTecnologica salvar(AreaTecnologica area);

	void remover(AreaTecnologica area);

	Optional<AreaTecnologica> buscarPeloID(String ID);

	default AreaTecnologica buscarPeloIDOuExcecao(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Area tecnologica com ID %s não encontrada", ID)));
	}
	
	List<AreaTecnologica> buscarTodasPeloTituloContem(String titulo);
	
	List<AreaTecnologica> buscarTodas();
	
}
