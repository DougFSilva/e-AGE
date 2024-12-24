package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaCursoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.CursoResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComCursoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCursoException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaCurso {

	private final CursoRepository repository;
	private final AreaTecnologicaRepository areaTecnologicaRepository;
	private final ImagemService imagemService;
	private final ValidaCurso validador;
	private final LogPadrao log;
	
	public CursoResposta criar(CriaCursoForm form) {
		try {
			validador.validarUnicoTitulo(form.titulo());
			Curso curso = construirCurso(form);
			Curso cursoSalvo = repository.salvar(curso);
			log.info(String.format("Criado curso %s", cursoSalvo.getTitulo()));
			return CursoResposta.deCurso(cursoSalvo);
		} catch (ErroDeValidacaoDeCursoException | ObjetoNaoEncontradoException | ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar curso %s : %s", form.titulo(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComCursoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar curso %s : %s", form.titulo(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComCursoException(mensagem, e);
		}
	}
	
	private Curso construirCurso(CriaCursoForm form) {
		AreaTecnologica area = areaTecnologicaRepository.buscarPeloIDOuThrow(form.areaTecnologicaID());
		return new Curso(form.modalidade(), area, form.titulo(), form.descricao(), imagemService.buscarImagemPadrao(TipoImagem.CURSO));
	}
}
