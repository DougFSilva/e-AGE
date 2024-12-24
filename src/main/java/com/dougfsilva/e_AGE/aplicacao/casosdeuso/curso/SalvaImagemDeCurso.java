package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.ValidaImagem;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.CursoResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComCursoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeImagemException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvaImagemDeCurso {

	private final CursoRepository repository;
	private final ImagemService imagemService;
	private final ValidaImagem validador;
	private final LogPadrao log;
	
	public CursoResposta salvar(String ID, MultipartFile imagem) {
		try {
			validador.validar(imagem);
			Curso curso = repository.buscarPeloIDOuThrow(ID);
			String url = imagemService.salvar(imagem, TipoImagem.CURSO, GeraNomeDeImagem.peloCurso(curso));
			curso.setImagem(url);
			Curso cursoSalvo = repository.salvar(curso);
			log.info(String.format("Salva imagem de curso %s", cursoSalvo.getTitulo()));
			return CursoResposta.deCurso(cursoSalvo);
		} catch (ErroDeValidacaoDeImagemException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao salvar imagem de curso com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComCursoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao salvar imagem de curso com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComCursoException(mensagem, e);
		}
	}
}
