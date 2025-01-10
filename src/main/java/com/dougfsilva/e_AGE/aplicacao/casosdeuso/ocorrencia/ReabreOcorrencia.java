package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReabreOcorrencia {

	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	
	public Ocorrencia reabrirPeloID(String ID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
		garantirOcorrenciaFechada(ocorrencia);
		garantirOcorrenciaNaoAssinada(ocorrencia);
		validarPermissoesDeUsuario(ocorrencia);
		ocorrencia.setStatus(OcorrenciaStatus.ABERTA);
		return repository.salvar(ocorrencia);
	}
	
	private void garantirOcorrenciaFechada(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.FECHADA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("Ocorrência %s está %s, somente uma ocorrêncua fechada pode ser reaberta", 
							ocorrencia.getID(), ocorrencia.getStatus().name().toLowerCase()));
		}
	}
	
	private Ocorrencia garantirOcorrenciaNaoAssinada(Ocorrencia ocorrencia) {
		ocorrencia.setAssinaturaAluno(null);
		if (ocorrencia.getAssinaturaResponsavel() != null) {
			ocorrencia.getAssinaturaResponsavel().setAssinatura(null);
			ocorrencia.getAssinaturaResponsavel().setID(null);
			ocorrencia.getAssinaturaResponsavel().setTimestamp(null);
			ocorrencia.getAssinaturaResponsavel().setResponsavelPelaColetaDaAssinatura(null);
		}
		return ocorrencia;
	}
	
	private void validarPermissoesDeUsuario(Ocorrencia ocorrencia) {
		Funcionario funcionarioAutenticado = buscaFuncionario.buscarPeloUsuarioAutenticado();
	    boolean usuarioGestor = funcionarioAutenticado.getUsuario().contemPerfil(TipoPerfil.GESTOR);
	    boolean usuarioRelator = ocorrencia.getAberturaDeOcorrencia().getRelator().equals(funcionarioAutenticado);
	    boolean restrita = ocorrencia.getRestrita();
	    boolean encaminhada = ocorrencia.getEncaminhada();
	    if (encaminhada && !usuarioGestor) {
	        throw new ErroDeValidacaoDeOcorrenciaException(
	            "Somente usuário com perfil gestor pode reabrir uma ocorrência encaminhada");
	    }
	    if (restrita && !usuarioGestor && !usuarioRelator) {
	    	throw new ErroDeValidacaoDeOcorrenciaException(
		            String.format("A ocorrência %s é restrita. Apenas o relator ou um usuário gestor pode reabrí-la", ocorrencia.getID()));
	    }
	}
}
