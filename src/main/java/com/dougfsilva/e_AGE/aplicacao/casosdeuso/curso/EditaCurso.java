package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaCursoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.CursoResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologicaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComCursoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCursoException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaCurso {

	private final CursoRepository repository;
	private final AreaTecnologicaRepository areaTecnologicaRepository;
	private final ValidaCurso validador;
	private final LogPadrao log;

	public CursoResposta editar(EditaCursoForm form) {
		try {
			Curso curso = repository.buscarPeloIDOuThrow(form.ID());
			Curso cursoAtualizado = editarDados(form, curso);
			Curso cursoSalvo = repository.salvar(cursoAtualizado);
			log.info(String.format("Editado curso %s", cursoSalvo.getTitulo()));
			return CursoResposta.deCurso(cursoSalvo);
		} catch (ErroDeValidacaoDeCursoException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao editar curso %s : %s", form.titulo(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComCursoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar curso %s : %s", form.titulo(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComCursoException(mensagem, e);
		}
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
