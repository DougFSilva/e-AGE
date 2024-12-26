package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.modulo;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.ModuloResposta;
import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaModulo {

	private final ModuloRepository repository;
	
	public ModuloResposta buscarPeloID(String ID) {
		return ModuloResposta.deModulo(repository.buscarPeloIDOuThrow(ID));
	}
	
	public Pagina<ModuloResposta> buscarPeloCurso(Curso curso, RequisicaoDePagina requisicao){
		return ModuloResposta.dePagina(repository.buscarPeloCurso(curso, requisicao));
	}

	public Pagina<ModuloResposta> buscarTodos(RequisicaoDePagina requisicao){
		return ModuloResposta.dePagina(repository.buscarTodos(requisicao));
	}
}
