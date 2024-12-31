package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.aplicacao.formulario.CriaTurmaForm;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCursoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaTurma {

	private final TurmaRepository repository;
	private final CursoRepository cursoRepository;
	private final ValidaTurma validador;
	
	public Turma criar(CriaTurmaForm form) {
		Curso curso = cursoRepository.buscarPeloIDOuThrow(form.cursoID());
		garantirCursoAberto(curso);
		validador.validarUnicoCodigoPorCurso(curso, form.codigo());
		Turma turma = new Turma(form.codigo(), curso,  true);
		return repository.salvar(turma);
	}
	
	private void garantirCursoAberto(Curso curso) {
		if (!curso.getAberto()) {
			throw new ErroDeValidacaoDeCursoException(String.format("Não é possível criar turma para o curso %s pois o mesmo está fechado", curso.getTitulo()));
		}
	}
}
