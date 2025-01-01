package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiCurso {

	private final CursoRepository repository;
	private final TurmaRepository turmaRepository;
	private final ImagemService imagemService;
	
	public void excluirPeloID(String ID) {
		Curso curso = repository.buscarPeloIDOuThrow(ID);
		garantirCursoSemTurmas(curso);
		imagemService.remover(TipoImagem.CURSO, GeraNomeDeImagem.peloCurso(curso));
		repository.excluir(curso);
	}
	
	private void garantirCursoSemTurmas(Curso curso) {
		if (turmaRepository.existePeloCurso(curso)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir o curso porque existem turmas associadas a ele");
		}
	}
}
