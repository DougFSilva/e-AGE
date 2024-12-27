package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComFuncionarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiFuncionario {

	private final FuncionarioRepository repository;
	private final OcorrenciaRepository ocorrenciaRepository;
	private final UsuarioRepository usuarioRepository;
	private final ImagemService imagemService;
	private final LogPadrao log;
	
	public void excluirPeloID(String ID) {
		try {
			Funcionario funcionario = repository.buscarPeloIDOuThrow(ID);
			garantirFuncionarioSemOcorrencias(funcionario);
			imagemService.remover(TipoImagem.FUNCIONARIO, GeraNomeDeImagem.pelaPessoa(funcionario));
			excluirUsuario(funcionario);
			repository.excluir(funcionario);
			log.info(String.format("Excluído funcionário %s com ID %s", funcionario.getNome(), funcionario.getID()));
		} catch (ObjetoNaoEncontradoException | ErroDeEntidadeComVinculosException | ErroDeOperacaoComUsuarioException e) {
			String mensagem = String.format("Erro ao excluir funcionário com ID %s : %s", ID, e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao excluir funcionário com ID %s : %s", ID, e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		}
	}
	
	private void garantirFuncionarioSemOcorrencias(Funcionario funcionario) {
		if (ocorrenciaRepository.existePeloFuncionario(funcionario)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir o funcionário porque existem ocorrências associadas a ele");
		}
	}
	
	private void excluirUsuario(Funcionario funcionario) {
		if (funcionario.getUsuario() != null) {
			usuarioRepository.excluir(funcionario.getUsuario());
			log.info(String.format("Excluido usuario %s", funcionario.getUsuario().getNomeDeUsuario()));
		}
	}
}
