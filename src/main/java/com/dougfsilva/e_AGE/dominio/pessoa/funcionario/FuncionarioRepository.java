package com.dougfsilva.e_AGE.dominio.pessoa.funcionario;

import java.time.LocalDate;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface FuncionarioRepository {

	Funcionario salvar(Funcionario funcionario);

	void remover(Funcionario funcionario);

	Optional<Funcionario> buscarPeloID(String ID);

	default Funcionario buscarPeloIDOuExcecao(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Funcionario com ID %s não encontrado", ID)));
	}

	Pagina<Funcionario> buscarTodosPeloNomeContem(String nome, RequisicaoDePagina requisicaoDePagina);

	Pagina<Funcionario> buscarTodosPeloSexo(Sexo sexo, RequisicaoDePagina requisicaoDePagina);

	Pagina<Funcionario> buscarTodosPelaDataDeNascimentoEntre(LocalDate dataInicial, LocalDate dataFinal,
			RequisicaoDePagina requisicaoDePagina);

	Pagina<Funcionario> buscarTodosPeloEstado(String estado, RequisicaoDePagina requisicao);

	Pagina<Funcionario> buscarTodosPelaCidade(String cidade, RequisicaoDePagina requisicao);

	Pagina<Funcionario> buscarTodosPeloBairro(String bairro, RequisicaoDePagina requisicao);

	Pagina<Funcionario> buscarTodos(RequisicaoDePagina requisicaoDePagina);
}
