package com.dougfsilva.e_AGE.dominio.endereco;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

public interface EnderecoRepository {

	Endereco salvar(Endereco endereco);

	void remover(Endereco endereco);

	Optional<Endereco> buscarPeloID(String ID);

	default Endereco buscarPeloIDOuExcecao(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Endereço com ID %s não encontrado", ID)));
	}
	
}
