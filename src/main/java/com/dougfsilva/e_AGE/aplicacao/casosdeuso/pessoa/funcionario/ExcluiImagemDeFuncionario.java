package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.FuncionarioResposta;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComFuncionarioException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiImagemDeFuncionario {

	private final FuncionarioRepository repository;
	private final ImagemService imagemService;
	private final LogPadrao log;
	
	public FuncionarioResposta excluirPeloID(String ID){
		try {
			Funcionario funcionario = repository.buscarPeloIDOuThrow(ID);
			imagemService.remover(TipoImagem.FUNCIONARIO, GeraNomeDeImagem.pelaPessoa(funcionario));
			funcionario.setFoto(imagemService.buscarImagemPadrao(TipoImagem.ALUNO));
			Funcionario funcionarioSalvo = repository.salvar(funcionario);
			log.info(String.format("Excluida imagem de funcionário %s com ID %s", funcionarioSalvo.getNome(), funcionarioSalvo.getID()));
			return FuncionarioResposta.deFuncionario(funcionarioSalvo);
		} catch (ObjetoNaoEncontradoException e) {
			String mensagem = String.format("Erro ao excluir imagem de funcionário com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir imagem de funcionário com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		}
	}
}
