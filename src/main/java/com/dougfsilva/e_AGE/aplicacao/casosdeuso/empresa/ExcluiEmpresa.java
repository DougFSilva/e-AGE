package com.dougfsilva.e_AGE.aplicacao.casosdeuso.empresa;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComEmpresaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiEmpresa {

	private final EmpresaRepository repository;
	private final AlunoRepository alunoRepository;
	private final LogPadrao log;
	
	public void excluirPeloID(String ID) {
		try {
			Empresa empresa = repository.buscarPeloIDOuThrow(ID);
			garantirEmpresaSemAluno(empresa);
			repository.excluir(empresa);
			log.info(String.format("Excluída empresa %s", empresa.getNome()));
		} catch (ObjetoNaoEncontradoException | ErroDeEntidadeComVinculosException e) {
			String mensagem = String.format("Erro ao excluir empresa com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComEmpresaException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir empresa com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComEmpresaException(mensagem, e);
		}
	}
	
	private void garantirEmpresaSemAluno(Empresa empresa) {
		if (alunoRepository.existePelaEmpresa(empresa)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir a empresa porque existem alunos associados a ela");
		}
	}
}
