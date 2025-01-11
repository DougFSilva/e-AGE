package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.formulario.EditaCursoForm;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaCurso {

	private final CursoRepository repository;
	private final AreaTecnologicaRepository areaTecnologicaRepository;
	private final ValidaCurso validador;

	public Curso editar(EditaCursoForm form) {
		Curso curso = editarDados(form);
		return repository.salvar(curso);
	}
	
	private Curso editarDados(EditaCursoForm form) {
		Curso curso = repository.buscarPeloIDOuThrow(form.ID());
		if (form.modalidade() != null) {
			curso.setModalidade(form.modalidade());
		}
		if (form.areaTecnologicaID() != null 
				&& !form.areaTecnologicaID().isBlank() 
				&& !form.areaTecnologicaID().equals(curso.getAreaTecnologica().getID())) {
			AreaTecnologica area = areaTecnologicaRepository.buscarPeloIDOuThrow(form.areaTecnologicaID());
			curso.setAreaTecnologica(area);
		}
		if (form.titulo() != null && !form.titulo().isBlank() && !form.titulo().equals(curso.getTitulo())) {
			validador.validarUnicoTitulo(form.titulo());
			curso.setTitulo(form.titulo());
		}
		if (form.descricao() != null && !form.descricao().isBlank()) {
			curso.setDescricao(form.descricao());
		}
		return curso;
	}
	
}
