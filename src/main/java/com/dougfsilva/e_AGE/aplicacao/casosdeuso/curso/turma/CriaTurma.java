package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaTurmaForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.TurmaResposta;
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
	
	public TurmaResposta criar(CriaTurmaForm form) {
		Curso curso = cursoRepository.buscarPeloIDOuThrow(form.cursoID());
		validador.validarUnicoCodigoPorCurso(curso, form.codigo());
		Turma turma = construirTurma(form, curso);
		Turma turmaSalva = repository.salvar(turma);
		return TurmaResposta.deTurma(turmaSalva);
	}
	
	private Turma construirTurma(CriaTurmaForm form, Curso curso) {
		return new Turma(form.codigo(), curso, form.dataDeAbertura());
	}
}
