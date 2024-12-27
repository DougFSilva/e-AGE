package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.MatriculaAlunoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatriculaAluno {

	private final MatriculaRepository repository;
	private final ModuloRepository moduloRepository;
	private final AlunoRepository alunoRepository;
	private final ValidaMatricula validador;
	private final LogPadrao log;
	
	public MatriculaResposta matricular(MatriculaAlunoForm form) {
		try {
			Matricula matricula = construirMatricula(form);
			Matricula matriculaSalva = repository.salvar(matricula);
			log.info(String.format("Aluno %s matriculado no módulo %s com ID %s", 
					matriculaSalva.getAluno().getNome(), matriculaSalva.getModulo().getCodigo(), matriculaSalva.getModulo().getID()));
			return MatriculaResposta.deMatricula(matriculaSalva);
		} catch (ErroDeValidacaoDeMatriculaException | ErroDeValidacaoDeCamposException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao matricular aluno com ID %s : %s", form.alunoID(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao matricular aluno com ID %s : %s", form.alunoID(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		}
	}
	
	private Matricula construirMatricula(MatriculaAlunoForm form) {
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(form.alunoID());
		validador.validarUnicoAlunoPorModulo(modulo, aluno);
		validador.validarUnicoRegistro(form.registro());
		return new Matricula(form.registro(), modulo, aluno, form.dataDaMatricula(), MatriculaStatus.ATIVA);
	}
	
}
