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
public class DefineModuloComoFinal {

	private final ModuloRepository repository;
	private final LogPadrao log;
	
	public void definirPeloID(String ID) {
		try {
			Modulo modulo = repository.buscarPeloIDOuThrow(ID);
			List<Modulo> modulos = repository.buscarPelaTurma(modulo.getTurma());
			modulos.removeIf(m -> m.equals(modulo));
			modulos.sort(Comparator.comparingInt(Modulo::getNumeroDoModulo));
			for (int i =0; i < modulos.size(); i++) {
				modulos.get(i).setNumeroDoModulo(i + 1);
				modulos.get(i).setModuloFinal(false);
			}
			modulo.setModuloFinal(true);
			modulo.setNumeroDoModulo(modulos.size() + 1);
			modulos.add(modulo);
			repository.salvarTodos(modulos);
			log.info(String.format("Definido modulo %s com ID %s como final", modulo.getCodigo(), modulo.getID()));
		} catch ( ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao definir como final o módulo com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao definir como final o módulo com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		}
	}
}
