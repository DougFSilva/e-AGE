package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import java.util.Comparator;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefineModuloComoFinal {

	private final ModuloRepository repository;
	
	public Modulo definirPeloID(String ID) {
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
		return modulo;
	}
}
