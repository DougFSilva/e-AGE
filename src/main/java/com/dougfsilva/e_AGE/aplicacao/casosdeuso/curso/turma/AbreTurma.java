package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeTurmaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbreTurma {

	private final TurmaRepository repository;
	
	public Turma abrirPeloID(String ID) {
		Turma turma = repository.buscarPeloIDOuThrow(ID);
		garantirCursoAberto(turma.getCurso());
		turma.setAberta(true);
		return repository.salvar(turma);
	}
	
	private void garantirCursoAberto(Curso curso) {
		if (!curso.getAberto()) {
			throw new ErroDeValidacaoDeTurmaException(String.format("Não é possível abrir turma pois o curso %s está fechado", curso.getTitulo()));
		}
	}
}
