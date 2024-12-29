package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.ValidaImagem;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.CursoResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvaImagemDeCurso {

	private final CursoRepository repository;
	private final ImagemService imagemService;
	private final ValidaImagem validador;
	
	public CursoResposta salvar(String ID, MultipartFile imagem) {
		validador.validar(imagem);
		Curso curso = repository.buscarPeloIDOuThrow(ID);
		String url = imagemService.salvar(imagem, TipoImagem.CURSO, GeraNomeDeImagem.peloCurso(curso));
		curso.setImagem(url);
		return CursoResposta.deCurso(repository.salvar(curso));
	}
}
