package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.FuncionarioResposta;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaFuncionario {

	private final FuncionarioRepository repository;
	
	public FuncionarioResposta buscarPeloID(String ID) {
		return FuncionarioResposta.deFuncionario(repository.buscarPeloIDOuThrow(ID));
	}
	
	public Pagina<FuncionarioResposta> buscarPeloSexo(Sexo sexo, RequisicaoDePagina requisicaoDePagina){
		return FuncionarioResposta.dePagina(repository.buscarPeloSexo(sexo, requisicaoDePagina));
	}

	public Pagina<FuncionarioResposta> buscarPelaDataDeNascimento(LocalDate dataInicial, LocalDate dataFinal,
			RequisicaoDePagina requisicaoDePagina){
		return FuncionarioResposta.dePagina(repository.buscarPelaDataDeNascimento(dataInicial, dataFinal, requisicaoDePagina));
	}

	public Pagina<FuncionarioResposta> buscarPeloEstado(String estado, RequisicaoDePagina requisicao){
		return FuncionarioResposta.dePagina(repository.buscarPeloEstado(estado, requisicao));
	}

	public Pagina<FuncionarioResposta> buscarPelaCidade(String cidade, RequisicaoDePagina requisicao){
		return FuncionarioResposta.dePagina(repository.buscarPelaCidade(cidade, requisicao));
	}

	public Pagina<FuncionarioResposta> buscarPeloBairro(String bairro, RequisicaoDePagina requisicao){
		return FuncionarioResposta.dePagina(repository.buscarPeloBairro(bairro, requisicao));
	}

	public Pagina<FuncionarioResposta> buscarTodos(RequisicaoDePagina requisicaoDePagina){
		return FuncionarioResposta.dePagina(repository.buscarTodos(requisicaoDePagina));
	}
}
