package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaModulo {

	private final ModuloRepository repository;

	public void validarUnicoCodigoPorTurma(Turma turma, String codigo) {
		if (repository.existePelaTurmaECodigo(turma, codigo)) {
			throw new ErroDeValidacaoDeModuloException(
					String.format("Código de módulo %s já existe para a turma %s", codigo, turma.getCodigo()));
		}
	}
}
