package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatricularAlunosNoProximoModulo {

	private final MatriculaRepository repository;
	private final ModuloRepository moduloRepository;
	private final LogPadrao log;
	
	public void avancarPeloIDDoModulo(String ID) {
		try {
			Modulo modulo = moduloRepository.buscarPeloIDOuThrow(ID);
			garantirModuloNaoFinal(modulo);
			List<Matricula> matriculas = repository.buscarPeloModulo(modulo);
			garantirModuloSemMatriculasAtivas(matriculas);
			Modulo proximoModulo = buscarProximoModulo(modulo);
			List<Matricula> novasMatriculas = construirNovasMatriculas(proximoModulo, matriculas);
			List<Matricula> matriculasSalvas = repository.salvarTodas(novasMatriculas);
			matriculasSalvas.forEach(matricula -> 
			log.info(String.format("Aluno %s matriculado no módulo %s com ID %s", 
					matricula.getAluno().getNome(), matricula.getModulo().getCodigo(), matricula.getModulo().getID())));
		} catch (ErroDeValidacaoDeMatriculaException | ErroDeValidacaoDeModuloException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao matricular alunos do modulo com ID %s para o próximo módulo : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao matricular alunos do modulo com ID %s para o próximo módulo : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComMatriculaException(mensagem, e);
		}
		
	}
	
	private void garantirModuloSemMatriculasAtivas(List<Matricula> matriculas) {
		if (matriculas.stream().anyMatch(matricula -> matricula.getStatus() == MatriculaStatus.MATRICULA_ATIVA)) {
			throw new ErroDeValidacaoDeMatriculaException("Não é possível avançar os alunos para o próximo módulo enquanto houver matrículas ativas");
		}
	}
	
	private void garantirModuloNaoFinal(Modulo modulo) {
		if (modulo.getModuloFinal()) {
			throw new ErroDeValidacaoDeModuloException("Não é possível avançar os alunos para o próximo módulo, pois o módulo atual já é o módulo final");
		}
	}
	
	private Modulo buscarProximoModulo(Modulo modulo) {
		Integer numero = modulo.getNumeroDoModulo() + 1;
		return moduloRepository.buscarPeloNumeroDoModulo(numero).orElseThrow(() -> 
			new ObjetoNaoEncontradoException(String.format("Módulo com número %s não encontrado", numero)));
	}
	
	private List<Matricula> construirNovasMatriculas(Modulo proximoModulo, List<Matricula> matriculas){
		List<Matricula> novasMatriculas = matriculas.stream()
				.filter(matricula -> matricula.getStatus() == MatriculaStatus.ALUNO_APROVADO)
	            .filter(matricula -> !repository.existePeloModuloEAluno(proximoModulo, matricula.getAluno()))
				.map(matricula -> new Matricula(matricula.getRegistro(), 
							proximoModulo, 
							matricula.getAluno(), 
							matricula.getDataDaMatricula(), 
							MatriculaStatus.MATRICULA_ATIVA)).collect(Collectors.toList());
		return novasMatriculas;
	}
	
}
