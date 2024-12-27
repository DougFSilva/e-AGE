package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaModuloForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.ModuloResposta;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaModulo {

	private final ModuloRepository repository;
	private final TurmaRepository turmaRepository;
	private final ValidaModulo validador;
	private final LogPadrao log;
	
	public ModuloResposta editar(EditaModuloForm form) {
		try {
			Modulo modulo = repository.buscarPeloIDOuThrow(form.ID());
			Modulo moduloEditado = editarDados(form, modulo);
			Modulo moduloSalvo = repository.salvar(moduloEditado);
			log.info(String.format("Editado módulo %s da turma %s", moduloSalvo.getCodigo(), moduloSalvo.getTurma().getCodigo()));
			return ModuloResposta.deModulo(moduloSalvo);
		} catch (ErroDeValidacaoDeModuloException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao editar módulo com ID %s : %s", form.ID(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao editar módulo com ID %s : %s", form.ID(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		}
	}
	
	private Modulo editarDados(EditaModuloForm form, Modulo modulo) {
		if (form.turmaID() != null && !form.turmaID().isBlank() && !form.turmaID().equalsIgnoreCase(modulo.getTurma().getID())) {
			Turma turma = turmaRepository.buscarPeloIDOuThrow(form.turmaID());
			modulo.setTurma(turma);
		}
		if (form.codigo() != null && !form.codigo().isBlank()) {
			validador.validarUnicoCodigoPorTurma(modulo.getTurma(), form.codigo());
			modulo.setCodigo(form.codigo());
		}
		return modulo;
	}
}
