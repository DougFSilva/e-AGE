package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComImagemException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final CursoRepository cursoRepository;
	private final ImagemService imagemService;
	private final LogPadrao log;
	
	public void excluirPeloID(String ID) {
		try {
			AreaTecnologica area = repository.buscarPeloIDOuThrow(ID);
			imagemService.remover(TipoImagem.AREA_TECNOLOGICA, GeraNomeDeImagem.pelaAreaTecnologica(area));
			repository.excluir(area);
			log.info(String.format("Excluida área tecnológica %s", area.getTitulo()));
		} catch (ObjetoNaoEncontradoException | ErroDeOperacaoComImagemException | ErroDeEntidadeComVinculosException e) {
			String mensagem = String.format("Erro ao excluir área tecnológica com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir área tecnológica com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		}
	}
	
	public void garantirAreaSemCursos(AreaTecnologica area) {
		if (cursoRepository.existePelaAreaTecnologica(area)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir a área tecnológica porque existem cursos associados a ela");
		}
	}
}
