package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.ValidaImagem;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeImagemException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvaImagemDeAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final ImagemService imagemService;
	private final ValidaImagem validador;
	private final LogPadrao log;
	
	public AreaTecnologica salvar(String ID, MultipartFile imagem) {
		try {
			validador.validar(imagem);
			AreaTecnologica area = repository.buscarPeloIDOuThrow(ID);
			String url = imagemService.salvar(imagem, TipoImagem.AREA_TECNOLOGICA, GeraNomeDeImagem.pelaAreaTecnologica(area));
			area.setImagem(url);
			AreaTecnologica areaSalva = repository.salvar(area);
			log.info(String.format("Salva imagem de area tecnologica %s", areaSalva.getTitulo()));
			return areaSalva;
		} catch (ErroDeValidacaoDeImagemException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao salvar imagem de area tecnologica com ID %s ; %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao salvar imagem de area tecnologica com ID %s ; %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		}
	}
	
}
