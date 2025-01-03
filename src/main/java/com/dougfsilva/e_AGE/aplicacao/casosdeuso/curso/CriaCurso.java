package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.formulario.CriaCursoForm;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaCurso {

	private final CursoRepository repository;
	private final AreaTecnologicaRepository areaTecnologicaRepository;
	private final ImagemService imagemService;
	private final ValidaCurso validador;
	
	public Curso criar(CriaCursoForm form) {
		validador.validarUnicoTitulo(form.titulo());
		Curso curso = construirCurso(form);
		return repository.salvar(curso);
	}
	
	private Curso construirCurso(CriaCursoForm form) {
		AreaTecnologica area = areaTecnologicaRepository.buscarPeloIDOuThrow(form.areaTecnologicaID());
		return new Curso(form.modalidade(), area, form.titulo(), form.descricao(), true, imagemService.buscarImagemPadrao(TipoImagem.CURSO));
	}
}
