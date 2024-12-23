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
	
	void remover(Aluno aluno);

	Optional<Aluno> buscarPeloID(String ID);
	
	default Aluno buscarPeloIDOuExcecao(String ID) {
	    return buscarPeloID(ID)
	        .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Aluno com ID %s não encontrado", ID)));
	}
	
	Pagina<Aluno> buscarTodosPeloNomeContem(String nome, RequisicaoDePagina requisicaoDePagina);
	
	Pagina<Aluno> buscarTodosPeloSexo(Sexo sexo, RequisicaoDePagina requisicaoDePagina);
	
	Pagina<Aluno> buscarTodosPelaEmpresa(Empresa empresa, RequisicaoDePagina requisicaoDePagina);
	
	Pagina<Aluno> buscarTodosPelaDataDeNascimentoEntre(LocalDate dataInicial, LocalDate dataFinal, RequisicaoDePagina requisicaoDePagina);
	
	Pagina<Aluno> buscarTodos(RequisicaoDePagina requisicaoDePagina);
}
