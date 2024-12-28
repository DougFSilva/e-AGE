package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import java.util.Comparator;
import java.util.List;

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
			List<Modulo> modulos = repository.buscarPelaTurma(modulo.getTurma());
			if (modulo.getModuloFinal() && modulos.size() > 1) {
				modulos.stream().max(Comparator.comparingInt(Modulo::getNumeroDoModulo)).get().setModuloFinal(true);
			}
			modulos.sort(Comparator.comparingInt(Modulo::getNumeroDoModulo));
			for (int i = 0; i < modulos.size(); i++) {
				modulos.get(i).setNumeroDoModulo(i + 1);
			}
			repository.excluir(modulo);
			repository.salvarTodos(modulos);
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
