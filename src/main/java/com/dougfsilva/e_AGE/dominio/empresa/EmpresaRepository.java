package com.dougfsilva.e_AGE.dominio.empresa;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface EmpresaRepository {

	Empresa salvar(Empresa empresa);

	void remover(Empresa empresa);

	Optional<Empresa> buscarPeloID(String ID);

	default Empresa buscarPeloIDOuExcecao(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Empresa com ID %s não encontrada", ID)));
	}
	
	Pagina<Aluno> buscarTodasPeloNomeContem(String nome, RequisicaoDePagina requisicaoDePagina);

	Pagina<Empresa> buscarTodasPeloEstado(String estado, RequisicaoDePagina requisicao);

	Pagina<Empresa> buscarTodasPelaCidade(String cidade, RequisicaoDePagina requisicao);

	Pagina<Empresa> buscarTodasPeloBairro(String bairro, RequisicaoDePagina requisicao);
	
	Pagina<Empresa> buscarTodas(RequisicaoDePagina requisicao);
}
