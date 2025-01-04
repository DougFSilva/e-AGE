package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.UsuarioAutenticado;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaFuncionario {

	private final FuncionarioRepository repository;
	private final UsuarioAutenticado usuarioAutenticado;
	
	public Funcionario buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}
	
	public Funcionario buscarPeloUsuarioAutenticado() {
		return repository.buscarPeloUsuario(usuarioAutenticado.buscarUsuarioAtualOuThrow()).orElseThrow(
				() -> new ObjetoNaoEncontradoException("Funcionário não encontrado pelo usuário autenticado"));
	}
	
	public Pagina<Funcionario> buscarPeloSexo(Sexo sexo, RequisicaoDePagina requisicaoDePagina){
		return repository.buscarPeloSexo(sexo, requisicaoDePagina);
	}

	public Pagina<Funcionario> buscarPelaDataDeNascimento(LocalDate dataInicial, LocalDate dataFinal,
			RequisicaoDePagina requisicaoDePagina){
		return repository.buscarPelaDataDeNascimento(dataInicial, dataFinal, requisicaoDePagina);
	}

	public Pagina<Funcionario> buscarPeloEstado(String estado, RequisicaoDePagina requisicao){
		return repository.buscarPeloEstado(estado, requisicao);
	}

	public Pagina<Funcionario> buscarPelaCidade(String cidade, RequisicaoDePagina requisicao){
		return repository.buscarPelaCidade(cidade, requisicao);
	}

	public Pagina<Funcionario> buscarPeloBairro(String bairro, RequisicaoDePagina requisicao){
		return repository.buscarPeloBairro(bairro, requisicao);
	}

	public Pagina<Funcionario> buscarTodos(RequisicaoDePagina requisicaoDePagina){
		return repository.buscarTodos(requisicaoDePagina);
	}
}
