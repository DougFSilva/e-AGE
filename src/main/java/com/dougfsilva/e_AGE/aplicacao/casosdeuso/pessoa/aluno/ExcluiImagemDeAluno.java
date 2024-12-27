package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.AlunoResposta;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAlunoException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiImagemDeAluno {

	private final AlunoRepository repository;
	private final ImagemService imagemService;
	private final LogPadrao log;
	
	public AlunoResposta excluirPeloID(String ID){
		try {
			Aluno aluno = repository.buscarPeloIDOuThrow(ID);
			imagemService.remover(TipoImagem.ALUNO, GeraNomeDeImagem.pelaPessoa(aluno));
			aluno.setFoto(imagemService.buscarImagemPadrao(TipoImagem.ALUNO));
			Aluno alunoSalvo = repository.salvar(aluno);
			log.info(String.format("Excluida imagem de aluno %s com ID %s", alunoSalvo.getNome(), alunoSalvo.getID()));
			return AlunoResposta.deAluno(alunoSalvo);
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao excluir imagem de aluno com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir imagem de aluno com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		}
	}
}
