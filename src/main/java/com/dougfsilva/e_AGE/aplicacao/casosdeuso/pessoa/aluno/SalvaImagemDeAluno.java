package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.ValidaImagem;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.AlunoResposta;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAlunoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeImagemException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvaImagemDeAluno {

	private final AlunoRepository repository;
	private final ImagemService imagemService;
	private final ValidaImagem validador;
	private final LogPadrao log;
	
	public AlunoResposta salvar(String ID, MultipartFile imagem) {
		try {
			validador.validar(imagem);
			Aluno aluno = repository.buscarPeloIDOuThrow(ID);
			String url = imagemService.salvar(imagem, TipoImagem.ALUNO, GeraNomeDeImagem.pelaPessoa(aluno));
			aluno.setFoto(url);
			Aluno alunoSalvo = repository.salvar(aluno);
			log.info(String.format("Salva imagem de aluno %s com ID %s", alunoSalvo.getNome(), alunoSalvo.getID()));
			return AlunoResposta.deAluno(alunoSalvo);
		} catch (ErroDeValidacaoDeImagemException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao salvar imagem de aluno com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao salvar imagem de aluno com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		}
	}
}
