package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeModuloException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatriculaAlunosNoProximoModulo {

	private final MatriculaRepository repository;
	private final ModuloRepository moduloRepository;
	
	public List<Matricula> avancarPeloIDdoModulo(String ID) {
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(ID);
		garantirModuloNaoFinal(modulo);
		List<Matricula> matriculas = repository.buscarPeloModulo(modulo);
		garantirModuloSemMatriculasAtivas(matriculas);
		Modulo proximoModulo = buscarProximoModulo(modulo);
		List<Matricula> novasMatriculas = construirNovasMatriculas(proximoModulo, matriculas);
		return repository.salvarTodas(novasMatriculas);
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
