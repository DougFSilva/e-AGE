package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.UsuarioAutenticado;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaAluno {

	private final AlunoRepository repository;
	private final EmpresaRepository empresaRepository;
	private final UsuarioAutenticado usuarioAutenticado;

	public Aluno buscarPeloID(String ID) {
		return repository.buscarPeloIDOuThrow(ID);
	}
	
	public Aluno buscarPeloUsuarioAutenticado() {
		return repository.buscarPeloUsuario(usuarioAutenticado.buscarUsuarioAtualOuThrow()).orElseThrow(() -> 
			new ObjetoNaoEncontradoException("Aluno não encontrado pelo usuário autenticado"));
	}

	public Pagina<Aluno> buscarPeloNomeContem(String nome, RequisicaoDePagina requisicaoDePagina) {
		return repository.buscarPeloNomeContem(nome, requisicaoDePagina);
	}

	public Pagina<Aluno> buscarPeloSexo(Sexo sexo, RequisicaoDePagina requisicaoDePagina) {
		return repository.buscarPeloSexo(sexo, requisicaoDePagina);
	}

	public Pagina<Aluno> buscarPeloIDdaEmpresa(String empresaID, RequisicaoDePagina requisicaoDePagina) {
		Empresa empresa = empresaRepository.buscarPeloIDOuThrow(empresaID);
		return repository.buscarPelaEmpresa(empresa, requisicaoDePagina);
	}

	public Pagina<Aluno> buscarPelaDataDeNascimento(LocalDate dataInicial, LocalDate dataFinal,
			RequisicaoDePagina requisicaoDePagina) {
		return repository.buscarPelaDataDeNascimento(dataInicial, dataFinal, requisicaoDePagina);
	}

	public Pagina<Aluno> buscarPeloEstado(String estado, RequisicaoDePagina requisicao) {
		return repository.buscarPeloEstado(estado, requisicao);
	}

	public Pagina<Aluno> buscarPelaCidade(String cidade, RequisicaoDePagina requisicao) {
		return repository.buscarPelaCidade(cidade, requisicao);
	}

	public Pagina<Aluno> buscarPeloBairro(String bairro, RequisicaoDePagina requisicao) {
		return repository.buscarPeloBairro(bairro, requisicao);
	}

	public Pagina<Aluno> buscarTodos(RequisicaoDePagina requisicaoDePagina) {
		return repository.buscarTodos(requisicaoDePagina);
	}
}
