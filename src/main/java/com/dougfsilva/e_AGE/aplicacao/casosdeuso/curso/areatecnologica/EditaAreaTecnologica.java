package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaAreaTecnologicaForm;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final ValidaAreaTecnologica validador;
	
	public AreaTecnologica editar(EditaAreaTecnologicaForm form) {
		AreaTecnologica area = repository.buscarPeloIDOuThrow(form.ID());
		AreaTecnologica areaEditada = editarDados(form, area);
		return repository.salvar(areaEditada);
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
