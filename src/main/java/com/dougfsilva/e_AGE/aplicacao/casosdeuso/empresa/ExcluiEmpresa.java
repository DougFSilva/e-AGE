package com.dougfsilva.e_AGE.aplicacao.casosdeuso.empresa;

import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiEmpresa {

	private final EmpresaRepository repository;
	private final AlunoRepository alunoRepository;
	
	public void excluirPeloID(String ID) {
		Empresa empresa = repository.buscarPeloIDOuThrow(ID);
		garantirEmpresaSemAluno(empresa);
		repository.excluir(empresa);
	}
	
	private void garantirEmpresaSemAluno(Empresa empresa) {
		if (alunoRepository.existePelaEmpresa(empresa)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir a empresa porque existem alunos associados a ela");
		}
	}
}
