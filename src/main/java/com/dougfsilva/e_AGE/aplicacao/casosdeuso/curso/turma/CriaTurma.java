package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.aplicacao.dto.CriaTurmaForm;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaTurma {

	private final TurmaRepository repository;
	private final CursoRepository cursoRepository;
	private final ValidaTurma validador;
	
	public Turma criar(CriaTurmaForm form) {
		Curso curso = cursoRepository.buscarPeloIDOuThrow(form.cursoID());
		validador.validarUnicoCodigoPorCurso(curso, form.codigo());
		Turma turma = construirTurma(form, curso);
		return repository.salvar(turma);
	}
	
	private Turma construirTurma(CriaTurmaForm form, Curso curso) {
		return new Turma(form.codigo(), curso, form.dataDeAbertura());
	}
}
