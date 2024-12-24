package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final LogPadrao log;
	
	public void excluirPeloID(String ID) {
		try {
			AreaTecnologica area = repository.buscarPeloIDOuThrow(ID);
			repository.excluir(area);
			log.info(String.format("Excluida area tecnologica com ID %s", ID));
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao excluir area tecnologica com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir area tecnologica com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		}
	}
}
