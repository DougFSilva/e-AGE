package com.dougfsilva.e_AGE.aplicacao.casosdeuso.empresa;

import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaEmpresa {

	private final EmpresaRepository repository;
	
	public Empresa buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}
	
	public Pagina<Empresa> buscarPeloNomeContem(String nome, RequisicaoDePagina requisicaoDePagina){
		return repository.buscarPeloNomeContem(nome, requisicaoDePagina);
	}

	public Pagina<Empresa> buscarPeloEstado(String estado, RequisicaoDePagina requisicao){
		return repository.buscarPeloEstado(estado, requisicao);
	}

	public Pagina<Empresa> buscarPelaCidade(String cidade, RequisicaoDePagina requisicao){
		return repository.buscarPelaCidade(cidade, requisicao);
	}

	public Pagina<Empresa> buscarPeloBairro(String bairro, RequisicaoDePagina requisicao){
		return repository.buscarPeloBairro(bairro, requisicao);
	}
	
	public Pagina<Empresa> buscarTodas(RequisicaoDePagina requisicao){
		return repository.buscarTodas(requisicao);
	}
	
}
