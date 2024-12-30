package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.dto.EditaCursoForm;
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
		Curso curso = repository.buscarPeloIDOuThrow(form.ID());
		Curso cursoEditado = editarDados(form, curso);
		return repository.salvar(cursoEditado);
	}
	
	private Curso editarDados(EditaCursoForm form, Curso curso) {
		if (form.modalidade() != null) {
			curso.setModalidade(form.modalidade());
		}
		if (form.areaTecnologicaID() != null 
				&& !form.areaTecnologicaID().isBlank() 
				&& !form.areaTecnologicaID().equalsIgnoreCase(curso.getAreaTecnologica().getID())) {
			AreaTecnologica area = areaTecnologicaRepository.buscarPeloIDOuThrow(form.areaTecnologicaID());
			curso.setAreaTecnologica(area);
		}
		if (form.titulo() != null && !form.titulo().isBlank() && !form.titulo().equalsIgnoreCase(curso.getTitulo())) {
			validador.validarUnicoTitulo(form.titulo());
			curso.setTitulo(form.titulo());
		}
		if (form.descricao() != null && !form.descricao().isBlank()) {
			curso.setDescricao(form.descricao());
		}
		return curso;
	}
	
}
