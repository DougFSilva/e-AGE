package com.dougfsilva.e_AGE.dominio.empresa;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface EmpresaRepository {

	Empresa salvar(Empresa empresa);

	void excluir(Empresa empresa);

	Optional<Empresa> buscarPeloID(String ID);

	default Empresa buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Empresa com ID %s não encontrada", ID)));
	}
	
	Pagina<Aluno> buscarPeloNomeContem(String nome, RequisicaoDePagina requisicaoDePagina);

	Pagina<Empresa> buscarPeloEstado(String estado, RequisicaoDePagina requisicao);

	Pagina<Empresa> buscarPelaCidade(String cidade, RequisicaoDePagina requisicao);

	Pagina<Empresa> buscarPeloBairro(String bairro, RequisicaoDePagina requisicao);
	
	Pagina<Empresa> buscarTodas(RequisicaoDePagina requisicao);
}
