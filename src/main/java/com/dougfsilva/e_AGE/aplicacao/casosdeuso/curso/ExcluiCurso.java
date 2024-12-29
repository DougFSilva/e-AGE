package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.certificado.CertificadoRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiCurso {

	private final CursoRepository repository;
	private final ModuloRepository moduloRepository;
	private final TurmaRepository turmaRepository;
	private final CertificadoRepository certificadoRepository;
	private final ImagemService imagemService;
	
	public void excluirPeloID(String ID) {
		Curso curso = repository.buscarPeloIDOuThrow(ID);
		garantirCursoSemCertificados(curso);
		garantirCursoSemTurmas(curso);
		imagemService.remover(TipoImagem.CURSO, GeraNomeDeImagem.peloCurso(curso));
		moduloRepository.excluirTodosPeloCurso(curso);
		repository.excluir(curso);
	}
	
	private void garantirCursoSemCertificados(Curso curso) {
		if (certificadoRepository.existePeloCurso(curso)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir o curso porque existem certificados associados a ele");
		}
	}
	
	private void garantirCursoSemTurmas(Curso curso) {
		if (turmaRepository.existePeloCurso(curso)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir o curso porque existem turmas associadas a ele");
		}
	}
}
