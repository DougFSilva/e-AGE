package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import java.util.Arrays;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario.CriaUsuario;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAlunoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaUsuarioParaAluno {

	private final AlunoRepository repository;
	private final CriaUsuario criaUsuario;
	private final LogPadrao log;
	
	public void criarPeloID(String ID) {
		try {
			Aluno aluno = repository.buscarPeloIDOuThrow(ID);
			validarAlunoSemUsuario(aluno);
			Usuario usuario = criaUsuario.criarUsuarioDefaultParaPessoa(aluno, Arrays.asList(TipoPerfil.ALUNO));
			aluno.setUsuario(usuario);
			Aluno alunoSalvo = repository.salvar(aluno);
			log.info(String.format("Criado usuário para aluno %s com ID %s", alunoSalvo.getNome(), alunoSalvo.getID()));
		}  catch (ObjetoNaoEncontradoException | ErroDeOperacaoComUsuarioException | ErroDeValidacaoDeUsuarioException  e) {
			String mensagem = String.format("Erro ao criar usuário para aluno com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar usuário para aluno com ID%s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		}
	}
	
	private void validarAlunoSemUsuario(Aluno aluno) {
		if (aluno.getUsuario() != null) {
			throw new ErroDeValidacaoDeUsuarioException(String.format("O aluno %s de ID %s já possui usuário", aluno.getNome(), aluno.getID()));
		}
	}
}
