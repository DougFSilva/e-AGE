package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.ValidaImagem;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.FuncionarioResposta;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComFuncionarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeImagemException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvaImagemDeFuncionario {

	private final FuncionarioRepository repository;
	private final ImagemService imagemService;
	private final ValidaImagem validador;
	private final LogPadrao log;
	
	public FuncionarioResposta salvar(String ID, MultipartFile imagem) {
		try {
			validador.validar(imagem);
			Funcionario funcionario = repository.buscarPeloIDOuThrow(ID);
			String url = imagemService.salvar(imagem, TipoImagem.FUNCIONARIO, GeraNomeDeImagem.pelaPessoa(funcionario));
			funcionario.setFoto(url);
			Funcionario funcionarioSalvo = repository.salvar(funcionario);
			log.info(String.format("Salva imagem de funcionário %s com ID %s", funcionarioSalvo.getNome(), funcionarioSalvo.getID()));
			return FuncionarioResposta.deFuncionario(funcionarioSalvo);
		} catch (ErroDeValidacaoDeImagemException | ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao salvar imagem de funcionário com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao salvar imagem de funcionário com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		}
	}
}
