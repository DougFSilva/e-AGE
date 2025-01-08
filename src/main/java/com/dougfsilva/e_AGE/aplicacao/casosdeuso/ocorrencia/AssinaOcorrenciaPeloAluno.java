package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno.BuscaAluno;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.AssinaturaDeOcorrenciaAluno;
import com.dougfsilva.e_AGE.dominio.ocorrencia.ChaveSecreta;
import com.dougfsilva.e_AGE.dominio.ocorrencia.CodificadorDeAssinatura;
import com.dougfsilva.e_AGE.dominio.ocorrencia.GeradorDeSalt;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssinaOcorrenciaPeloAluno {
	
	private final OcorrenciaRepository repository;
	private final CodificadorDeAssinatura codificador;
	private final GeradorDeSalt geradorDeSalt;
	private final ChaveSecreta chaveSecreta;
	private final BuscaAluno buscaAluno;
	private final EnviaNotificacaoDeOcorrencia notifica;

	public Ocorrencia assinarOcorrenciaPeloID(String ID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
		garantirOcorrenciaPertencenteAoUsuarioAutenticado(ocorrencia);
		AssinaturaDeOcorrenciaAluno assinatura = gerarAssinatura(ocorrencia);
		ocorrencia.setAssinaturaAluno(assinatura);
		ocorrencia.setStatus(OcorrenciaStatus.ASSINADA);
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notificar(ocorrenciaSalva);
		return ocorrenciaSalva;
	}
	
	private void notificar(Ocorrencia ocorrencia) {
		Boolean encaminhada = ocorrencia.getEncaminhada();
		Boolean alunoMenorDeIdade = ocorrencia.getMatricula().getAluno().calcularIdade() < 18;
		if (!encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAluno(ocorrencia, OperacaoDeOcorrencia.ASSINADA);
		} else if (encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestores(ocorrencia, OperacaoDeOcorrencia.ASSINADA);
		} else if (!encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaResponsavel(ocorrencia, OperacaoDeOcorrencia.ASSINADA);
		} else if (encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestoresEResponsavel(ocorrencia, OperacaoDeOcorrencia.ASSINADA);
		}
	}
	
	private AssinaturaDeOcorrenciaAluno gerarAssinatura(Ocorrencia ocorrencia) {
		String salt = geradorDeSalt.gerar();
		LocalDateTime timestamp = LocalDateTime.now();
		String dadosParaHash = ocorrencia.getID() + ocorrencia.getMatricula().getID() + timestamp + salt + chaveSecreta.buscarChave();
		String assinatura = codificador.codificar(dadosParaHash);
		return new AssinaturaDeOcorrenciaAluno(assinatura, timestamp, salt);
	}
	
	private void garantirOcorrenciaPertencenteAoUsuarioAutenticado(Ocorrencia ocorrencia) {
		Aluno aluno = buscaAluno.buscarPeloUsuarioAutenticado();
		if(!ocorrencia.getMatricula().getAluno().equals(aluno)) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("Ocorrência %s não pertence ao aluno autenticado %s", ocorrencia.getID(), aluno.getNome()));
		}
	}
	
}
