package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AprovaAlunosPeloModulo {

	private final MatriculaRepository repository;
	private final ModuloRepository moduloRepository;
	
	public List<MatriculaResposta> aprovarPeloIDdoModulo(String ID){
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(ID);
		List<Matricula> matriculas = repository.buscarPeloModulo(modulo)
				.stream()
				.filter(matricula -> matricula.getStatus() == MatriculaStatus.MATRICULA_ATIVA)
				.collect(Collectors.toList());
		return repository.salvarTodas(matriculas).stream().map(MatriculaResposta::new).collect(Collectors.toList());
	}
}
