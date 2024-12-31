package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCursoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FechaCurso {

	private final CursoRepository repository;
	private final TurmaRepository turmaRepository;
	
	public Curso fecharPeloID(String ID) {
		Curso curso = repository.buscarPeloIDOuThrow(ID);
		garantirNenhumaTurmaAberta(curso);
		curso.setAberto(false);
		return repository.salvar(curso);
	}
	
	private void garantirNenhumaTurmaAberta(Curso curso) {
		if (turmaRepository.existePeloCursoEStatusAberta(curso, true)) {
			throw new ErroDeValidacaoDeCursoException(
					String.format("Não é possível fechar o curso %s pois ainda há turmas associadas a ele", curso.getTitulo()));
		}
		
	}
}
