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
	}
	
}
