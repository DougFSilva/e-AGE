package com.dougfsilva.e_AGE.dominio.pessoa.funcionario;

import java.time.LocalDate;
import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

public interface FuncionarioRepository {

	Funcionario salvar(Funcionario funcionario);

	void excluir(Funcionario funcionario);

	Optional<Funcionario> buscarPeloID(String ID);

	default Funcionario buscarPeloIDOuThrow(String ID) {
		return buscarPeloID(ID).orElseThrow(
				() -> new ObjetoNaoEncontradoException(String.format("Funcionário com ID %s não encontrado", ID)));
	}

	Pagina<Funcionario> buscarPeloNomeContem(String nome, RequisicaoDePagina requisicaoDePagina);

	Pagina<Funcionario> buscarPeloSexo(Sexo sexo, RequisicaoDePagina requisicaoDePagina);

	Pagina<Funcionario> buscarPelaDataDeNascimentoEntre(LocalDate dataInicial, LocalDate dataFinal,
			RequisicaoDePagina requisicaoDePagina);

	Pagina<Funcionario> buscarPeloEstado(String estado, RequisicaoDePagina requisicao);

	Pagina<Funcionario> buscarPelaCidade(String cidade, RequisicaoDePagina requisicao);

	Pagina<Funcionario> buscarPeloBairro(String bairro, RequisicaoDePagina requisicao);

	Pagina<Funcionario> buscarTodos(RequisicaoDePagina requisicaoDePagina);
}
