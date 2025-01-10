package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiOcorrencia {

	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	private final EnviaNotificacaoDeOcorrencia notifica;
	
	public void excluirPeloID(String ID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
		validarPermissoesDeUsuario(ocorrencia);
		garantirOcorrenciaAberta(ocorrencia);
		garantirOcorrenciaSemTratamento(ocorrencia);
		repository.excluir(ocorrencia);
		notificar(ocorrencia);
	}
	
	private void validarPermissoesDeUsuario(Ocorrencia ocorrencia) {
		Funcionario funcionarioAutenticado = buscaFuncionario.buscarPeloUsuarioAutenticado();
	    boolean usuarioRelator = ocorrencia.getAberturaDeOcorrencia().getRelator().equals(funcionarioAutenticado);
	    if (!usuarioRelator) {
	    	throw new ErroDeValidacaoDeOcorrenciaException(
		            String.format("Apenas o relator pode excluir a ocorrência", ocorrencia.getID()));
	    }
	}
	
	private void garantirOcorrenciaAberta(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.ABERTA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência aberta pode ser excluída",
							ocorrencia.getID(), ocorrencia.getStatus().name().toLowerCase()));
		}
	}
	
	private void garantirOcorrenciaSemTratamento(Ocorrencia ocorrencia) {
		if (ocorrencia.getTratamento().size() > 0) {
			throw new ErroDeValidacaoDeOcorrenciaException("Somente ocorrência sem tratamento pode ser excluída");
		}
	}
	
	private void notificar(Ocorrencia ocorrencia) {
		Boolean encaminhada = ocorrencia.getEncaminhada();
		Boolean alunoMenorDeIdade = ocorrencia.getMatricula().getAluno().menorDeIdade();
		if (!encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAluno(ocorrencia, OperacaoDeOcorrencia.EXCLUIDA);
		} else if (encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestores(ocorrencia, OperacaoDeOcorrencia.EXCLUIDA);
		} else if (!encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaResponsavel(ocorrencia, OperacaoDeOcorrencia.EXCLUIDA);
		} else {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestoresEResponsavel(ocorrencia, OperacaoDeOcorrencia.EXCLUIDA);
		}
	}
}
