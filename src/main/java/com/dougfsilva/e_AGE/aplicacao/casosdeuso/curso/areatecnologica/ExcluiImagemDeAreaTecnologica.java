package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiImagemDeAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final ImagemService imagemService;
	
	public AreaTecnologica excluirPeloID(String ID){
		AreaTecnologica area = repository.buscarPeloIDOuThrow(ID);
		imagemService.remover(TipoImagem.AREA_TECNOLOGICA, GeraNomeDeImagem.pelaAreaTecnologica(area));
		area.setImagem(imagemService.buscarImagemPadrao(TipoImagem.AREA_TECNOLOGICA));
		AreaTecnologica areaSalva = repository.salvar(area);
		return areaSalva;
	}
}
