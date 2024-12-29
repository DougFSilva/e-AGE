package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiAluno {

	private final AlunoRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final OcorrenciaRepository ocorrenciaRepository;
	private final UsuarioRepository usuarioRepository;

	public void excluirPeloID(String ID) {
		Aluno aluno = repository.buscarPeloIDOuThrow(ID);
		garantirAlunoSemMatriculas(aluno);
		garantirAlunoSemOcorrencias(aluno);
		excluirUsuario(aluno);
		repository.excluir(aluno);
	}
	
	private void garantirAlunoSemMatriculas(Aluno aluno) {
		if (matriculaRepository.existePeloAluno(aluno)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir o aluno porque existem matriculas associadas a ele");
		}
	}
	
	private void garantirAlunoSemOcorrencias(Aluno aluno) {
		if (ocorrenciaRepository.existePeloAluno(aluno)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir o aluno porque existem ocorrências associadas a ele");
		}
	}
	
	private void excluirUsuario(Aluno aluno) {
		if (aluno.getUsuario() != null) {
			usuarioRepository.excluir(aluno.getUsuario());
		}
	}
}
