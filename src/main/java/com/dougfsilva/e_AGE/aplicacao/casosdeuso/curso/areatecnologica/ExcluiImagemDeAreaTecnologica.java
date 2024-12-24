package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiImagemDeAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final ImagemService imagemService;
	private final LogPadrao log;
	
	public AreaTecnologica excluirPeloID(String ID){
		try {
			AreaTecnologica area = repository.buscarPeloIDOuThrow(ID);
			imagemService.remover(TipoImagem.AREA_TECNOLOGICA, GeraNomeDeImagem.pelaAreaTecnologica(area));
			area.setImagem(imagemService.buscarImagemPadrao(TipoImagem.AREA_TECNOLOGICA));
			AreaTecnologica areaSalva = repository.salvar(area);
			log.info(String.format("Excluida imagem de area tecnologica %s", areaSalva.getTitulo()));
			return areaSalva;
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao excluir imagem de area tecnologica com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir imagem de area tecnologica com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		}
	}
}
