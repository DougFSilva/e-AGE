package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.turma;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaTurmaForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.TurmaResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComTurmaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeTurmaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaTurma {

	private final TurmaRepository repository;
	private final CursoRepository cursoRepository;
	private final ValidaTurma validador;
	private final LogPadrao log;
	
	public TurmaResposta criar(CriaTurmaForm form) {
		try {
			Curso curso = cursoRepository.buscarPeloIDOuThrow(form.cursoID());
			validador.validarUnicoCodigoPorCurso(curso, form.codigo());
			Turma turma = construirTurma(form, curso);
			Turma turmaSalva = repository.salvar(turma);
			log.info(String.format("Criada Turma %s para o curso %s", turmaSalva.getCodigo(), turmaSalva.getCurso().getTitulo()));
			return TurmaResposta.deTurma(turmaSalva);
		} catch (ErroDeValidacaoDeTurmaException | ObjetoNaoEncontradoException | ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar turma %s : %s", form.codigo(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComTurmaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar turma %s : %s", form.codigo(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComTurmaException(mensagem, e);
		}
	}
	
	private Turma construirTurma(CriaTurmaForm form, Curso curso) {
		return new Turma(form.codigo(), curso, form.dataDeAbertura());
	}
}
