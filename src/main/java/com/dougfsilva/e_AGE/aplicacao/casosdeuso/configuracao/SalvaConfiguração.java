package com.dougfsilva.e_AGE.aplicacao.casosdeuso.configuracao;

import com.dougfsilva.e_AGE.dominio.configuracao.ChaveDeConfiguracao;
import com.dougfsilva.e_AGE.dominio.configuracao.Configuracao;
import com.dougfsilva.e_AGE.dominio.configuracao.ConfiguracaoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvaConfiguração {

	private final ConfiguracaoRepository repository;
	
	 public Configuracao salvar(ChaveDeConfiguracao chave, String valor) {
	        return repository.buscarPelaChave(chave)
	                .map(configuracao -> atualizarValor(configuracao, valor))
	                .orElseGet(() -> criarNovaConfiguracao(chave, valor));
	    }

	    private Configuracao atualizarValor(Configuracao configuracao, String valor) {
	        configuracao.setValor(valor);
	        return repository.salvar(configuracao);
	    }

	    private Configuracao criarNovaConfiguracao(ChaveDeConfiguracao chave, String valor) {
	        Configuracao novaConfiguracao = new Configuracao(chave, valor);
	        return repository.salvar(novaConfiguracao);
	    }
}
