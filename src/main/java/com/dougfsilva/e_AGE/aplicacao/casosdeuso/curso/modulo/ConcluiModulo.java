package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloStatus;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConcluiModulo {

	private final ModuloRepository repository;
	
	public Modulo concluirPeloID(String ID) {
		Modulo modulo = repository.buscarPeloIDOuThrow(ID);
		garantirModuloIniciado(modulo);
		modulo.setStatus(ModuloStatus.CONCLUIDO);
		return repository.salvar(modulo);
	}
	
	private void garantirModuloIniciado(Modulo modulo) {
		if (modulo.getStatus() != ModuloStatus.INICIADO) {
			throw new ErroDeValidacaoDeModuloException(
					String.format("Não é possível concluir o módulo %s pois ele ainda não foi iniciado", modulo.getCodigo()));
		}
	}
}
