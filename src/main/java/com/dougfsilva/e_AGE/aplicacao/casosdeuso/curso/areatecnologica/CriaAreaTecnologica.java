package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaAreaTecnologicaForm;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final ImagemService imagemService;
	private final ValidaAreaTecnologica validador;

	public AreaTecnologica criar(CriaAreaTecnologicaForm form) {
		validador.validarUnicoTitulo(form.titulo());
		AreaTecnologica area = new AreaTecnologica(form.titulo(), form.descricao(), imagemService.buscarImagemPadrao(TipoImagem.AREA_TECNOLOGICA));
		AreaTecnologica areaSalva = repository.salvar(area);
		return areaSalva;
	}
}
