package com.dougfsilva.e_AGE.dominio.configuracao;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

public interface ConfiguracaoRepository {

	Configuracao salvar(Configuracao configuracao);

	Optional<Configuracao> buscarPelaChave(ChaveDeConfiguracao chave);

	default Configuracao buscarPelaChaveOuThrow(ChaveDeConfiguracao chave) {
		return buscarPelaChave(chave).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Chave de configuracao %s não encontrada", chave.name())));
	}

	List<Configuracao> buscarTodas();
	
	Boolean ExistePelaChave(ChaveDeConfiguracao chave);
}
