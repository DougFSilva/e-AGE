package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaAreaTecnologicaForm;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final ImagemService imagemService;
	private final ValidaAreaTecnologica validador;
	private final LogPadrao log;
	
	public AreaTecnologica criar(CriaAreaTecnologicaForm form) {
		try {
			validador.validarUnicoTitulo(form.titulo());
			AreaTecnologica area = new AreaTecnologica(
					form.titulo(), 
					form.descricao(), 
					imagemService.buscarImagemPadrao(TipoImagem.AREA_TECNOLOGICA));
			AreaTecnologica areaSalva = repository.salvar(area);
			log.info(String.format("Criada area tecnologica %s", areaSalva.getTitulo()));
			return areaSalva;
		} catch (ErroDeValidacaoDeAreaTecnologicaException | ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar area tecnologica %s : %s", form.titulo(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar area tecnologica %s : %s", form.titulo(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		}
	}
}
