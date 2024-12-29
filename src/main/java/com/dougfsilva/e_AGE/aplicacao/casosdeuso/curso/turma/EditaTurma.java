package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaTurmaForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.TurmaResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaTurma {

	private final TurmaRepository repository;
	private final CursoRepository cursoRepository;
	private final ValidaTurma validador;
	
	public TurmaResposta editar(EditaTurmaForm form) {
		Turma turma = repository.buscarPeloIDOuThrow(form.ID());
		Turma turmaAtualizada = editarDados(form, turma);
		Turma turmaEditada = repository.salvar(turmaAtualizada);
		return TurmaResposta.deTurma(turmaEditada);
	}
	
	private Turma editarDados(EditaTurmaForm form, Turma turma) {
		if (form.cursoID() != null && !form.cursoID().isBlank() && !form.cursoID().equalsIgnoreCase(turma.getCurso().getID())) {
			Curso curso = cursoRepository.buscarPeloIDOuThrow(form.cursoID());
			turma.setCurso(curso);
		}
		if (form.codigo() != null && !form.codigo().isBlank() && !form.codigo().equalsIgnoreCase(turma.getCodigo())) {
			validador.validarUnicoCodigoPorCurso(turma.getCurso(), form.codigo());
			turma.setCodigo(form.codigo());
		}
		if (form.dataDeAbertura() != null) {
			turma.setDataDeAbertura(form.dataDeAbertura());
		}
		return turma;
	}
}
