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
	private final CodificadorDeAssinatura codificadorDeAssinatura;
	private final GeradorDeSalt geradorDeSalt;
	private final ChaveSecreta chaveSecreta;
	private final BuscaAluno buscaAluno;

	public Ocorrencia assinarOcorrenciaPeloID(String ID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
		garantirOcorrenciaFechada(ocorrencia);
		garantirOcorrenciaPertencenteAoUsuarioAutenticado(ocorrencia);
		AssinaturaDeOcorrenciaAluno assinatura = gerarAssinatura(ocorrencia);
		ocorrencia.setAssinaturaAluno(assinatura);
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		return ocorrenciaSalva;
	}
	
	private AssinaturaDeOcorrenciaAluno gerarAssinatura(Ocorrencia ocorrencia) {
		String salt = geradorDeSalt.gerar();
		LocalDateTime timestamp = LocalDateTime.now();
		String dadosParaHash = ocorrencia.getID() + ocorrencia.getMatricula().getID() + timestamp + salt + chaveSecreta.buscarChave();
		String assinatura = codificadorDeAssinatura.codificar(dadosParaHash);
		return new AssinaturaDeOcorrenciaAluno(assinatura, timestamp, salt);
	}
	
	private void garantirOcorrenciaFechada(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.FECHADA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência fechada pode ser assinada pelo aluno",
							ocorrencia.getID(), ocorrencia.getStatus().name()));
		}
	}
	
	private void garantirOcorrenciaPertencenteAoUsuarioAutenticado(Ocorrencia ocorrencia) {
		Aluno aluno = buscaAluno.buscarPeloUsuarioAutenticado();
		if(!ocorrencia.getMatricula().getAluno().equals(aluno)) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("Ocorrência %s não pertence ao aluno autenticado %s", ocorrencia.getID(), aluno.getNome()));
		}
	}
	
}
