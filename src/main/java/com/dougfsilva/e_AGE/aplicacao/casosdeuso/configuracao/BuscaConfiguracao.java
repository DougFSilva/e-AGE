package com.dougfsilva.e_AGE.aplicacao.casosdeuso.configuracao;

import java.util.HashMap;
import java.util.Map;

import com.dougfsilva.e_AGE.dominio.configuracao.ChaveDeConfiguracao;
import com.dougfsilva.e_AGE.dominio.configuracao.ConfiguracaoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaConfiguracao {

	private final ConfiguracaoRepository repository;

	public Map<ChaveDeConfiguracao, String> buscarTodas() {
		Map<ChaveDeConfiguracao, String> configuracoes = new HashMap<ChaveDeConfiguracao, String>();
		repository.buscarTodas().stream().map(config -> configuracoes.put(config.getChave(), config.getValor()));
		return configuracoes;
	}
}
