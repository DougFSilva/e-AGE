package com.dougfsilva.e_AGE.dominio.pessoa.aluno;

import java.time.LocalDate;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface AlunoRepository {
	
	Aluno salvar(Aluno aluno);
	
	void excluir(Aluno aluno);

	Optional<Aluno> buscarPeloID(String ID);
	
	default Aluno buscarPeloIDOuThrow(String ID) {
	    return buscarPeloID(ID)
	        .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Aluno com ID %s não encontrado", ID)));
	}
	
	Pagina<Aluno> buscarPeloNomeContem(String nome, RequisicaoDePagina requisicaoDePagina);
	
	Pagina<Aluno> buscarPeloSexo(Sexo sexo, RequisicaoDePagina requisicaoDePagina);
	
	Pagina<Aluno> buscarPelaEmpresa(Empresa empresa, RequisicaoDePagina requisicaoDePagina);
	
	Pagina<Aluno> buscarPelaDataDeNascimentoEntre(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicaoDePagina);
	
	Pagina<Aluno> buscarPeloEstado(String estado, RequisicaoDePagina requisicao);
	
	Pagina<Aluno> buscarPelaCidade(String cidade, RequisicaoDePagina requisicao);
	
	Pagina<Aluno> buscarPeloBairro(String bairro, RequisicaoDePagina requisicao);
	
	Pagina<Aluno> buscarTodos(RequisicaoDePagina requisicaoDePagina);
}
