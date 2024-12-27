package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiModulo {

	private final ModuloRepository repository;
	private final LogPadrao log;
	
	public void excluirPeloID(String ID) {
		try {
			Modulo modulo = repository.buscarPeloIDOuThrow(ID);
			repository.excluir(modulo);
			log.info(String.format("Excluído módulo %s da turma %s", modulo.getCodigo(), modulo.getTurma().getCodigo()));
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao excluir módulo com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir módulo com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		}
	}
	
}
