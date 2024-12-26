package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeTurmaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaTurma {

	private final TurmaRepository repository;
	
	public void validarUnicoCodigoPorCurso(Curso curso, String codigo) {
		if (repository.existePeloCursoECodigo(curso, codigo)) {
			throw new ErroDeValidacaoDeTurmaException(String.format("Código de turma %s já existe para o curso %s", codigo, curso.getTitulo()));
		}
	}
}
