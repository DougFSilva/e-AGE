package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia.assinatura;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.aplicacao.formulario.AssinaOcorrenciaPeloAlunoForm;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.AssinaturaDeAluno;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssinaOcorrenciaPeloAluno {
	
	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	private final AssinaturaDeAlunoService assinaturaService;

	public Ocorrencia assinar(AssinaOcorrenciaPeloAlunoForm form) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(form.ocorrenciaID());
		garantirOcorrenciaFechada(ocorrencia);
		Funcionario funcionarioAutenticado = buscaFuncionario.buscarPeloUsuarioAutenticado();
		validarPermissoesDeUsuario(ocorrencia, funcionarioAutenticado);
		AssinaturaDeAluno assinatura = assinaturaService.gerarAssinaturaCodificada(form.assinatura(), funcionarioAutenticado);
		ocorrencia.setAssinaturaAluno(assinatura);
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		return ocorrenciaSalva;
	}
	
	private void garantirOcorrenciaFechada(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.FECHADA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência fechada pode ser assinada pelo aluno",
							ocorrencia.getID(), ocorrencia.getStatus().name().toLowerCase()));
		}
	}
	
	private void validarPermissoesDeUsuario(Ocorrencia ocorrencia, Funcionario funcionarioAutenticado) {
	    boolean usuarioGestor = funcionarioAutenticado.getUsuario().contemPerfil(TipoPerfil.GESTOR);
	    boolean usuarioRelator = ocorrencia.getAberturaDeOcorrencia().getRelator().equals(funcionarioAutenticado);
	    boolean restrita = ocorrencia.getRestrita();
	    boolean encaminhada = ocorrencia.getEncaminhada();
	    if (encaminhada && !usuarioGestor) {
	    	throw new ErroDeValidacaoDeOcorrenciaException(
	    			"Somente usuário com perfil gestor pode coletar assinatura do aluno de uma ocorrência encaminhada");
	    }
	    if (restrita && !usuarioGestor && !usuarioRelator) {
	    	throw new ErroDeValidacaoDeOcorrenciaException(
		            String.format("A ocorrência %s é restrita. Apenas o relator ou um usuário gestor pode coletar a assinatura do aluno", 
		            		ocorrencia.getID()));
	    }
	}
	
}
