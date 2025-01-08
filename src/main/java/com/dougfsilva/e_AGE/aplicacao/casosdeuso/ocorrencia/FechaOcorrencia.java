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
public class FechaOcorrencia {

	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	private final EnviaNotificacaoDeOcorrencia notifica;
	
	public Ocorrencia fecharPeloID(String ID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
		garantirOcorrenciaAberta(ocorrencia);
		garantirOcorrenciaTratada(ocorrencia);
		Funcionario funcionarioAutenticado = buscarFuncionarioAutenticado();
		garantirUsuarioGestorSeOcorrenciaForEncaminhada(ocorrencia, funcionarioAutenticado);
		ocorrencia.setStatus(OcorrenciaStatus.FECHADA);
		ocorrencia.setDataDeFechamento(LocalDateTime.now());
		ocorrencia.setResponsavelPeloFechamento(funcionarioAutenticado);
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notificar(ocorrenciaSalva);
		return ocorrenciaSalva;
	}
	
	private void notificar(Ocorrencia ocorrencia) {
		Boolean encaminhada = ocorrencia.getEncaminhada();
		Boolean alunoMenorDeIdade = ocorrencia.getMatricula().getAluno().menorDeIdade();
		if (!encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAluno(ocorrencia, OperacaoDeOcorrencia.FECHADA);
		} else if (encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestores(ocorrencia, OperacaoDeOcorrencia.FECHADA);
		} else if (!encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaResponsavel(ocorrencia, OperacaoDeOcorrencia.FECHADA);
		} else if (encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestoresEResponsavel(ocorrencia, OperacaoDeOcorrencia.FECHADA);
		}
	}
	
	private void garantirOcorrenciaAberta(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.ABERTA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência aberta pode ser fechada",
							ocorrencia.getID(), ocorrencia.getStatus().name()));
		}
	}
	
	private void garantirOcorrenciaTratada(Ocorrencia ocorrencia) {
		if (ocorrencia.getTratamento().size() < 1) {
			throw new ErroDeValidacaoDeOcorrenciaException("Somente uma ocorrência com tratamentos pode ser fechada");
		}
	}
	
	private Funcionario buscarFuncionarioAutenticado() {
		return buscaFuncionario.buscarPeloUsuarioAutenticado();
	}
	
	private void garantirUsuarioGestorSeOcorrenciaForEncaminhada(Ocorrencia ocorrencia, Funcionario funcionarioAutenticado) {
		if (ocorrencia.getEncaminhada() && !funcionarioAutenticado.getUsuario().contemPerfil(TipoPerfil.GESTOR)) {
			throw new ErroDeValidacaoDeOcorrenciaException("Somente usuário com perfil Gestor pode fechar uma ocorrência encaminhada");
		}
	}
}
