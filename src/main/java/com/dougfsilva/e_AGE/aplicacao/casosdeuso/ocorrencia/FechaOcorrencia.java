package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FechaOcorrencia {

	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	private final EnviaNotificacaoDeOcorrencia notifica;
	
	public Ocorrencia fecharPeloID(String ID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
	}
	
	private Funcionario buscaFuncionarioAutenticado() {
		return buscaFuncionario.buscarPeloUsuarioAutenticado();
	}
}
