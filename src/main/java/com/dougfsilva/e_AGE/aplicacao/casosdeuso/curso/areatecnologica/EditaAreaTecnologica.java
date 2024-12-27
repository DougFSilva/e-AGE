package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaAreaTecnologicaForm;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeAreaTecnologicaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final ValidaAreaTecnologica validador;
	private final LogPadrao log;
	
	public AreaTecnologica editar(EditaAreaTecnologicaForm form) {
		try {
			AreaTecnologica area = repository.buscarPeloIDOuThrow(form.ID());
			AreaTecnologica areaEditada = editarDados(form, area);
			AreaTecnologica areaSalva = repository.salvar(areaEditada);
			log.info(String.format("Edita área tecnológica com ID %S", form.ID()));
			return areaSalva;
		} catch (ObjetoNaoEncontradoException | ErroDeValidacaoDeAreaTecnologicaException e) {
			String mensagem = String.format("Erro ao editar área tecnológica com ID %s : %s", form.ID(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar área tecnológica com ID %s : %s", form.ID(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAreaTecnologicaException(mensagem, e);
		}
	}
	
	private AreaTecnologica editarDados(EditaAreaTecnologicaForm form, AreaTecnologica area) {
		if (form.titulo() != null && !form.titulo().isBlank() && !form.titulo().equalsIgnoreCase(area.getTitulo())) {
			validador.validarUnicoTitulo(form.titulo());
			area.setTitulo(form.titulo());
		}
		if (form.descricao() != null && !form.descricao().isBlank()) {
			area.setDescricao(form.descricao());
		}
		return area;
	}
}
