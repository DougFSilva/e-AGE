package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final CursoRepository cursoRepository;
	private final ImagemService imagemService;
	
	public void excluirPeloID(String ID) {
		AreaTecnologica area = repository.buscarPeloIDOuThrow(ID);
		imagemService.remover(TipoImagem.AREA_TECNOLOGICA, GeraNomeDeImagem.pelaAreaTecnologica(area));
		repository.excluir(area);
	}
	
	public void garantirAreaSemCursos(AreaTecnologica area) {
		if (cursoRepository.existePelaAreaTecnologica(area)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir a área tecnológica porque existem cursos associados a ela");
		}
	}
}
