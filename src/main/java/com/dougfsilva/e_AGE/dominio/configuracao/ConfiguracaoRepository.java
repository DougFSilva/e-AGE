package com.dougfsilva.e_AGE.dominio.configuracao;

import java.util.List;

public interface ConfiguracaoRepository {

	Configuracao atualiza(Configuracao configuracao);
	
	Configuracao buscarPelaChave(ChaveDeConfiguracao chave);
	
	List<Configuracao> findAll();
	
	Boolean ExistePelaChave(ChaveDeConfiguracao chave);
}
