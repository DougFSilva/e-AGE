package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiImagemDeCurso {

	private final CursoRepository repository;
	private final ImagemService imagemService;
	
	public Curso excluirPeloID(String ID){
		Curso curso = repository.buscarPeloIDOuThrow(ID);
		imagemService.remover(TipoImagem.CURSO, GeraNomeDeImagem.peloCurso(curso));
		curso.setImagem(imagemService.buscarImagemPadrao(TipoImagem.CURSO));
		return repository.salvar(curso);
	}
}
