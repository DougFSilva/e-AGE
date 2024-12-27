package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAlunoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.matricula.MatriculaRepository;
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
	private final LogPadrao log;

	public void excluirPeloID(String ID) {
		try {
			Aluno aluno = repository.buscarPeloIDOuThrow(ID);
			garantirAlunoSemMatriculas(aluno);
			garantirAlunoSemOcorrencias(aluno);
			excluirUsuario(aluno);
			repository.excluir(aluno);
			log.info(String.format("Excluido aluno %s com ID %s", aluno.getNome(), aluno.getID()));
		} catch (ObjetoNaoEncontradoException | ErroDeEntidadeComVinculosException | ErroDeOperacaoComUsuarioException e) {
			String mensagem = String.format("Erro ao excluir aluno com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir aluno com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		}
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
