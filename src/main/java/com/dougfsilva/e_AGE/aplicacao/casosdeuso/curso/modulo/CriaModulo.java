package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaModuloForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.ModuloResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.CursoRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaModulo {

	private final ModuloRepository repository;
	private final CursoRepository cursoRepository;
	private final ValidaModulo validador;
	private final LogPadrao log;
	
	public ModuloResposta criar(CriaModuloForm form) {
		try {
			Curso curso = cursoRepository.buscarPeloIDOuThrow(form.cursoID());
			validador.validarUnicoCodigoPorCurso(curso, form.codigo());
			Modulo modulo = new Modulo(form.codigo(), curso);
			Modulo moduloSalvo = repository.salvar(modulo);
			log.info(String.format("Criado Modulo %s para o curso %s", moduloSalvo.getCodigo(), moduloSalvo.getCurso().getTitulo()));
			return ModuloResposta.deModulo(moduloSalvo);
		} catch (ErroDeValidacaoDeModuloException | ObjetoNaoEncontradoException | ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar modulo %s : %s", form.codigo(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar modulo %s : %s", form.codigo(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComModuloException(mensagem, e);
		}
	}	
}
