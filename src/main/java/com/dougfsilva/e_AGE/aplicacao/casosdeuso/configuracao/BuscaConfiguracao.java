package com.dougfsilva.e_AGE.aplicacao.casosdeuso.configuracao;

import java.util.List;

import com.dougfsilva.e_AGE.dominio.configuracao.Configuracao;
import com.dougfsilva.e_AGE.dominio.configuracao.ConfiguracaoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaConfiguracao {

	private final ConfiguracaoRepository repository;
	
	public List<Configuracao> buscarTodas() {
		return repository.buscarTodas();
	}
}
