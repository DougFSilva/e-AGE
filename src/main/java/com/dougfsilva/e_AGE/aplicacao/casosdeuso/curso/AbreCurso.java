package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbreCurso {

	private final CursoRepository repository;
	
	public Curso abrirPeloID(String ID) {
		Curso curso = repository.buscarPeloIDOuThrow(ID);
		curso.setAberto(true);
		return repository.salvar(curso);
	}
}
