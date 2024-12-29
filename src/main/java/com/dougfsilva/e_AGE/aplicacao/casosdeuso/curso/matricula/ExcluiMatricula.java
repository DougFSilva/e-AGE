package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiMatricula {

	private final MatriculaRepository repository;
	private final EvasaoRepository evasaoRepository;

	public void excluirPeloID(String ID) {
		Matricula matricula = repository.buscarPeloIDOuThrow(ID);
		excluirEvasao(matricula);
		repository.excluir(matricula);
	}

	private void excluirEvasao(Matricula matricula) {
		Optional<Evasao> evasao = evasaoRepository.buscarPelaMatricula(matricula);
		if (evasao.isPresent()) {
			evasaoRepository.excluir(evasao.get());
		}
	}
}
