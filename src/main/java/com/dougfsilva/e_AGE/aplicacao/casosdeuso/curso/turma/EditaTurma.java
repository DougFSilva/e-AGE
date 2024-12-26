package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaTurmaForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.TurmaResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComTurmaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeTurmaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaTurma {

	private final TurmaRepository repository;
	private final CursoRepository cursoRepository;
	private final ValidaTurma validador;
	private final LogPadrao log;
	
	public TurmaResposta editar(EditaTurmaForm form) {
		try {
			Turma turma = repository.buscarPeloIDOuThrow(form.ID());
			Turma turmaAtualizada = editarDados(form, turma);
			Turma turmaSalva = repository.salvar(turmaAtualizada);
			log.info(String.format("Editada turma %s", turmaSalva.getCodigo()));
			return TurmaResposta.deTurma(turmaSalva);
		} catch (ErroDeValidacaoDeTurmaException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao editar turma %s : %s", form.codigo(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComTurmaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar turma %s : %s", form.codigo(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComTurmaException(mensagem, e);
		}
	}
	
	private Turma editarDados(EditaTurmaForm form, Turma turma) {
		if (form.cursoID() != null && !form.cursoID().isBlank() && !form.cursoID().equalsIgnoreCase(turma.getCurso().getID())) {
			Curso curso = cursoRepository.buscarPeloIDOuThrow(form.cursoID());
			turma.setCurso(curso);
		}
		if (form.codigo() != null && !form.codigo().isBlank() && !form.codigo().equalsIgnoreCase(turma.getCodigo())) {
			validador.validarUnicoCodigoPorCurso(turma.getCurso(), form.codigo());
			turma.setCodigo(form.codigo());
		}
		if (form.dataDeAbertura() != null) {
			turma.setDataDeAbertura(form.dataDeAbertura());
		}
		return turma;
	}
}
