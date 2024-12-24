package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.CursoResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComCursoException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiImagemDeCurso {

	private final CursoRepository repository;
	private final ImagemService imagemService;
	private final LogPadrao log;
	
	public CursoResposta excluirPeloID(String ID){
		try {
			Curso curso = repository.buscarPeloIDOuThrow(ID);
			imagemService.remover(TipoImagem.CURSO, GeraNomeDeImagem.peloCurso(curso));
			curso.setImagem(imagemService.buscarImagemPadrao(TipoImagem.CURSO));
			Curso cursoSalvo = repository.salvar(curso);
			log.info(String.format("Excluida imagem de curso %s", cursoSalvo.getTitulo()));
			return CursoResposta.deCurso(cursoSalvo);
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao excluir imagem de curso com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComCursoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir imagem de curso com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComCursoException(mensagem, e);
		}
	}
}
