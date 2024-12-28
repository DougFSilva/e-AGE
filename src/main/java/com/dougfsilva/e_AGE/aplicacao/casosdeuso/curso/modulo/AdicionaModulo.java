package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import java.util.List;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaModuloForm;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdicionaModulo {

	private final ModuloRepository repository;
	private final TurmaRepository turmaRepository;
	private final ValidaModulo validador;
	private final LogPadrao log;
	
	public void criar(CriaModuloForm form) {
		try {
			Turma turma = turmaRepository.buscarPeloIDOuThrow(form.turmaID());
			validador.validarUnicoCodigoPorTurma(turma, form.codigo());
			Modulo modulo = new Modulo(form.codigo(), turma, form.dataDeAbertura());
			List<Modulo> modulos = repository.buscarPelaTurma(turma);
			modulos.forEach(m -> {
				m.setModuloFinal(false);
			});
			modulo.setNumeroDoModulo(modulos.size() + 1);
			modulo.setModuloFinal(true);
			modulos.add(modulo);
			repository.salvarTodos(modulos);
			log.info(String.format("Criado módulo %s para a turma %s", modulo.getCodigo(), modulo.getTurma().getCodigo()));
		} catch (ErroDeValidacaoDeModuloException | ObjetoNaoEncontradoException | ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar módulo %s : %s", form.codigo(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar módulo %s : %s", form.codigo(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		}
	}	
	
}
