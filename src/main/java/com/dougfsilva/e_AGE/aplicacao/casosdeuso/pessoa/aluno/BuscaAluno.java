package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.AlunoResposta;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.Sexo;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.RequisicaoDePagina;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaAluno {

	private final AlunoRepository repository;
	private final EmpresaRepository empresaRepository;

	public AlunoResposta buscarPeloID(String ID) {
		return AlunoResposta.deAluno(repository.buscarPeloIDOuThrow(ID));
	}

	public Pagina<AlunoResposta> buscarPeloNomeContem(String nome, RequisicaoDePagina requisicaoDePagina) {
		return AlunoResposta.dePagina(repository.buscarPeloNomeContem(nome, requisicaoDePagina));
	}

	public Pagina<AlunoResposta> buscarPeloSexo(Sexo sexo, RequisicaoDePagina requisicaoDePagina) {
		return AlunoResposta.dePagina(repository.buscarPeloSexo(sexo, requisicaoDePagina));
	}

	public Pagina<AlunoResposta> buscarPeloIDdaEmpresa(String empresaID, RequisicaoDePagina requisicaoDePagina) {
		Empresa empresa = empresaRepository.buscarPeloIDOuThrow(empresaID);
		return AlunoResposta.dePagina(repository.buscarPelaEmpresa(empresa, requisicaoDePagina));
	}

	public Pagina<AlunoResposta> buscarPelaDataDeNascimento(LocalDate dataInicial, LocalDate dataFinal,
			RequisicaoDePagina requisicaoDePagina) {
		return AlunoResposta.dePagina(repository.buscarPelaDataDeNascimento(dataInicial, dataFinal, requisicaoDePagina));
	}

	public Pagina<AlunoResposta> buscarPeloEstado(String estado, RequisicaoDePagina requisicao) {
		return AlunoResposta.dePagina(repository.buscarPeloEstado(estado, requisicao));
	}

	public Pagina<AlunoResposta> buscarPelaCidade(String cidade, RequisicaoDePagina requisicao) {
		return AlunoResposta.dePagina(repository.buscarPelaCidade(cidade, requisicao));
	}

	public Pagina<AlunoResposta> buscarPeloBairro(String bairro, RequisicaoDePagina requisicao) {
		return AlunoResposta.dePagina(repository.buscarPeloBairro(bairro, requisicao));
	}

	public Pagina<AlunoResposta> buscarTodos(RequisicaoDePagina requisicaoDePagina) {
		return AlunoResposta.dePagina(repository.buscarTodos(requisicaoDePagina));
	}
}
