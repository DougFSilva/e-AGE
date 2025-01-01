package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.reprova;

import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.reprova.Reprova;
import com.dougfsilva.e_AGE.dominio.curso.reprova.ReprovaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiReprova {

	private final ReprovaRepository repository;
	private final MatriculaRepository matriculaRepository;
	
	public void excluirPeloID(String ID) {
		Reprova reprova = repository.buscarPeloIDOuThrow(ID);
		reprova.getMatricula().setStatus(MatriculaStatus.ALUNO_MATRICULADO);
		matriculaRepository.salvar(reprova.getMatricula());
		repository.excluir(reprova);
	}
}
