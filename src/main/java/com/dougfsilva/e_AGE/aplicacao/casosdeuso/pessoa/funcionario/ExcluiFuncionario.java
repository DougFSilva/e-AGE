package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.GeraNomeDeImagem;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeEntidadeComVinculosException;
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
	
	public void excluirPeloID(String ID) {
		Funcionario funcionario = repository.buscarPeloIDOuThrow(ID);
		garantirFuncionarioSemOcorrencias(funcionario);
		imagemService.remover(TipoImagem.FUNCIONARIO, GeraNomeDeImagem.pelaPessoa(funcionario));
		excluirUsuario(funcionario);
		repository.excluir(funcionario);
	}
	
	private void garantirFuncionarioSemOcorrencias(Funcionario funcionario) {
		if (ocorrenciaRepository.existePeloResponsavelDeAberturaOuFechamentoOuEncerramentoOuAssinaturaDeAlunoOuResponsavel(funcionario)) {
			throw new ErroDeEntidadeComVinculosException("Não é possível excluir o funcionário porque existem ocorrências associadas a ele");
		}
	}
	
	private void excluirUsuario(Funcionario funcionario) {
		if (funcionario.getUsuario() != null) {
			usuarioRepository.excluir(funcionario.getUsuario());
		}
	}
}
