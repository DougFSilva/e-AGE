package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaAreaTecnologicaForm;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final LogPadrao log;
	
	public AreaTecnologica editar(EditaAreaTecnologicaForm form) {
		try {
			AreaTecnologica area = repository.buscarPeloIDOuThrow(form.ID());
			AreaTecnologica areaAtualizada = atualizarDados(form, area);
			AreaTecnologica areaSalva = repository.salvar(areaAtualizada);
			log.info(String.format("Edita Area tecnologica com ID %S", form.ID()));
			return areaSalva;
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao editar area tecnologica %s : %s", form.titulo(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar area tecnologica %s : %s", form.titulo(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		}
	}
	
	private AreaTecnologica atualizarDados(EditaAreaTecnologicaForm form, AreaTecnologica area) {
		if (form.titulo() != null && !form.titulo().isBlank()) {
			area.setTitulo(form.titulo());
		}
		if (form.descricao() != null && !form.descricao().isBlank()) {
			area.setDescricao(form.descricao());
		}
		return area;
	}
}
