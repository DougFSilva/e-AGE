package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import java.util.List;

import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.curso.turma.TurmaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaModulo {

	private final ModuloRepository repository;
	private final TurmaRepository turmaRepository;
	
	public Modulo buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}
	
	public List<Modulo> buscarPeloIDdaTurma(String turmaID){
		Turma turma = turmaRepository.buscarPeloIDOuThrow(turmaID);
		return repository.buscarPelaTurma(turma);
	}

	public Pagina<Modulo> buscarTodos(RequisicaoDePagina requisicao){
		return repository.buscarTodos(requisicao);
	}
}
