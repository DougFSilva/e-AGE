package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloStatus;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IniciaModulo {

	private final ModuloRepository repository;
	
	public Modulo iniciarPeloID(String ID) {
		Modulo modulo = repository.buscarPeloIDOuThrow(ID);
		garantirNenhumOutroModuloIniciadoNaTurma(modulo.getTurma());
		garantirModuloAnteriorConcluido(modulo);
		modulo.setStatus(ModuloStatus.INICIADO);
		return repository.salvar(modulo);
	}
	
	private void garantirNenhumOutroModuloIniciadoNaTurma(Turma turma) {
		if (repository.existePelaTurmaEStatus(turma, ModuloStatus.INICIADO)) {
			throw new ErroDeValidacaoDeModuloException(
					String.format("Não é possível iniciar módulo pois há outro módulo iniciado na turma %s", turma.getCodigo()));
		}
	}
	
	private void garantirModuloAnteriorConcluido(Modulo moduloAtual) {
		if (moduloAtual.getNumeroDoModulo() > 1) {
			repository.buscarPeloNumeroDoModulo(moduloAtual.getNumeroDoModulo() - 1).ifPresent(moduloAnterior -> {
				if (moduloAnterior.getStatus() != ModuloStatus.CONCLUIDO) {
					throw new ErroDeValidacaoDeModuloException(
							String.format("Não é possível iniciar o módulo pois o módulo anterior %s não foi concluído", moduloAnterior.getCodigo()));
				}
			});
		}
	}
	
}
