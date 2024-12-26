package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaModulo {

private final ModuloRepository repository;
	
	public void validarUnicoCodigoPorCurso(Curso curso, String codigo) {
		if (repository.existePeloCursoECodigo(curso, codigo)) {
			throw new ErroDeValidacaoDeModuloException(String.format("Código de módulo %s já existe para o curso %s", codigo, curso.getTitulo()));
		}
	}
}
