package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.evasao;

import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiEvasao {

	private final EvasaoRepository repository;
	private final MatriculaRepository matriculaRepository;
	
	public void excluirPeloID(String ID) {
		Evasao evasao = repository.buscarPeloIDOuThrow(ID);
		evasao.getMatricula().setStatus(MatriculaStatus.ALUNO_MATRICULADO);
		matriculaRepository.salvar(evasao.getMatricula());
		repository.excluir(evasao);
	}
}
