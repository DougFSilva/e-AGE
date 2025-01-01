package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import java.util.Comparator;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiModulo {

	private final ModuloRepository repository;
	
	public void excluirPeloID(String ID) {
		Modulo modulo = repository.buscarPeloIDOuThrow(ID);
		repository.excluir(modulo);
		List<Modulo> modulos = repository.buscarPelaTurma(modulo.getTurma());
		modulos.sort(Comparator.comparingInt(Modulo::getNumeroDoModulo));
		for (int i = 0; i < modulos.size(); i++) {
			modulos.get(i).setNumeroDoModulo(i + 1);
		}
		repository.salvarTodos(modulos);
	}
}
