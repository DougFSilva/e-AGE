package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.ValidaImagem;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvaImagemDeAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final ImagemService imagemService;
	private final ValidaImagem validador;

	public AreaTecnologica salvar(String ID, MultipartFile imagem) {
		validador.validar(imagem);
		AreaTecnologica area = repository.buscarPeloIDOuThrow(ID);
		String url = imagemService.salvar(imagem, TipoImagem.AREA_TECNOLOGICA, GeraNomeDeImagem.pelaAreaTecnologica(area));
		area.setImagem(url);
		return repository.salvar(area);
	}

}
