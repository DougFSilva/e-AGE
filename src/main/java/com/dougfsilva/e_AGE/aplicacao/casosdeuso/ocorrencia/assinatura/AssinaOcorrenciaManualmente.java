package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia.assinatura;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.aplicacao.formulario.AssinaOcorrenciaManualmenteForm;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.AssinaturaManual;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssinaOcorrenciaManualmente {

	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	private final AssinaturaManualService assinaturaManualService;
	
	public Ocorrencia assinar(AssinaOcorrenciaManualmenteForm form) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(form.ocorrenciaID());
		garantirOcorrenciaFechada(ocorrencia);
		Funcionario funcionarioAutenticado = buscaFuncionario.buscarPeloUsuarioAutenticado();
		validarPermissoesDeUsuario(ocorrencia, funcionarioAutenticado);
		AssinaturaManual assinaturaManual = assinaturaManualService.gerarAssinaturaCodificada(form.assinaturaResponsavel(), 
				funcionarioAutenticado, form.justificativa());
		ocorrencia.setAssinaturaManual(assinaturaManual);
		return repository.salvar(ocorrencia);
	}
	
	private void garantirOcorrenciaFechada(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.FECHADA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência fechada pode ser assinada manualmente",
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
	            "Somente usuário com perfil gestor pode assinar manualmente uma ocorrência encaminhada");
	    }
	    if (restrita && !usuarioGestor && !usuarioRelator) {
	    	throw new ErroDeValidacaoDeOcorrenciaException(
		            String.format("A ocorrência %s é restrita. Apenas o relator ou um usuário gestor pode assiná-la manualmente", 
		            		ocorrencia.getID()));
	    }
	}
	
}
