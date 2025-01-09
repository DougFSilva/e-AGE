package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EncerraOcorrencia {

	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	private final EnviaNotificacaoDeOcorrencia notifica;
	
	public Ocorrencia encerrarPeloID(String ID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
		Funcionario funcionario = buscaFuncionario.buscarPeloUsuarioAutenticado();
		validarPermissoesDeUsuario(ocorrencia, funcionario);
		garantirOcorrenciaAssinada(ocorrencia);
		ocorrencia.setStatus(OcorrenciaStatus.ENCERRADA);
		ocorrencia.setDataDeEncerramento(LocalDateTime.now());
		ocorrencia.setResponsavelPeloEncerramento(funcionario);
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notificar(ocorrenciaSalva);
		return ocorrenciaSalva;
	}
	
	private void validarPermissoesDeUsuario(Ocorrencia ocorrencia, Funcionario funcionarioAutenticado) {
	    boolean usuarioGestor = funcionarioAutenticado.getUsuario().contemPerfil(TipoPerfil.GESTOR);
	    if (ocorrencia.getEncaminhada() && !usuarioGestor) {
	        throw new ErroDeValidacaoDeOcorrenciaException(
	            "Somente usuário com perfil Gestor pode encerrar uma ocorrência encaminhada");
	    }
	}
	
	private void garantirOcorrenciaAssinada(Ocorrencia ocorrencia) {
		if (!ocorrencia.getAssinaturaAluno().assinada()) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("Não é possível encerrar a ocorrência %s porque ainda não foi assinada pelo aluno", ocorrencia.getID()));
		}
		if (ocorrencia.getMatricula().getAluno().menorDeIdade() && !ocorrencia.getAssinaturaResponsavel().assinada()) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("Não é possível encerrar a ocorrência %s porque ainda não foi assinada pelo responsável", ocorrencia.getID()));
		}
	}
	
	private void notificar(Ocorrencia ocorrencia) {
		Boolean encaminhada = ocorrencia.getEncaminhada();
		Boolean alunoMenorDeIdade = ocorrencia.getMatricula().getAluno().menorDeIdade();
		if (!encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAluno(ocorrencia, OperacaoDeOcorrencia.ENCERRADA);
		} else if (encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestores(ocorrencia, OperacaoDeOcorrencia.ENCERRADA);
		} else if (!encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaResponsavel(ocorrencia, OperacaoDeOcorrencia.ENCERRADA);
		} else {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestoresEResponsavel(ocorrencia, OperacaoDeOcorrencia.ENCERRADA);
		}
	}
	
}
