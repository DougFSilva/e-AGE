package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.RequisicaoParaEditarAreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final LogPadrao log;
	
	public AreaTecnologica editar(RequisicaoParaEditarAreaTecnologica requisicao) {
		try {
			AreaTecnologica area = repository.buscarPeloIDOuThrow(requisicao.ID());
			AreaTecnologica areaAtualizada = atualizarDados(requisicao, area);
			AreaTecnologica areaSalva = repository.salvar(areaAtualizada);
			log.info(String.format("Edita Area tecnologica com ID %S", requisicao.ID()));
			return areaSalva;
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao editar area tecnologica %s ; %s", requisicao.titulo(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar area tecnologica %s ; %s", requisicao.titulo(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		}
	}
	
	private AreaTecnologica atualizarDados(RequisicaoParaEditarAreaTecnologica requisicao, AreaTecnologica area) {
		if (requisicao.titulo() != null && !requisicao.titulo().isBlank()) {
			area.setTitulo(requisicao.titulo());
		}
		if (requisicao.descricao() != null && !requisicao.descricao().isBlank()) {
			area.setDescricao(requisicao.descricao());
		}
		return area;
	}
}
