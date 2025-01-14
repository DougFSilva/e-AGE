package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.areatecnologica;

import com.dougfsilva.e_AGE.aplicacao.formulario.EditaAreaTecnologicaForm;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaAreaTecnologica {

	private final AreaTecnologicaRepository repository;
	private final ValidaAreaTecnologica validador;
	
	public AreaTecnologica editar(EditaAreaTecnologicaForm form) {
		AreaTecnologica area = editarDados(form);
		return repository.salvar(area);
	}
	
	private AreaTecnologica editarDados(EditaAreaTecnologicaForm form) {
		AreaTecnologica area = repository.buscarPeloIDOuThrow(form.ID());
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
