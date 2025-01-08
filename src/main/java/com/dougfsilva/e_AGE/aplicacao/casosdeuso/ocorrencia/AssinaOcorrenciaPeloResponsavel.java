package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.ChaveSecreta;
import com.dougfsilva.e_AGE.dominio.ocorrencia.CodificadorDeAssinatura;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.ocorrencia.PINService;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssinaOcorrenciaPeloResponsavel {

	private OcorrenciaRepository repository;
	private PINService pinService;
	private CodificadorDeAssinatura codificadorDeAssinatura;
	private final ChaveSecreta chaveSecreta;
	private BuscaFuncionario buscaFuncionario;

	public Ocorrencia assinarOcorrenciaPeloID(String ID, String PIN) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
		garantirOcorrenciaFechada(ocorrencia);
		garantirAlunoMenorDeIdade(ocorrencia.getMatricula().getAluno());
		validarPIN(ocorrencia, PIN);
		Ocorrencia ocorrenciaAssinadaEAtualizada = gerarAssinaturaEAtualizarOcorrencia(ocorrencia, PIN);
		return repository.salvar(ocorrenciaAssinadaEAtualizada);
	}
	
	private void garantirOcorrenciaFechada(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.FECHADA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência fechada pode ser assinada pelo responsável",
							ocorrencia.getID(), ocorrencia.getStatus().name()));
		}
	}

	private void garantirAlunoMenorDeIdade(Aluno aluno) {
		if (!aluno.menorDeIdade()) {
			throw new ErroDeValidacaoDeOcorrenciaException(String.format(
					"Aluno %s é maior de idade, ele mesmo deve assinar a ocorrência, sem a necessidade de assinatura por parte do responsável",
					aluno.getNome()));
		}
	}

	private void validarPIN(Ocorrencia ocorrencia, String PIN) {
		if (pinService.comparar(PIN, ocorrencia.getAssinaturaResponsavel().getPIN())) {
			return;
		}
		Integer tentativasFalhas = ocorrencia.getAssinaturaResponsavel().getTentativasFalhasDeAssinatura();
		ocorrencia.getAssinaturaResponsavel().setTentativasFalhasDeAssinatura(tentativasFalhas + 1);
		if (ocorrencia.getAssinaturaResponsavel().getTentativasFalhasDeAssinatura() == 3) {
			ocorrencia.setStatus(OcorrenciaStatus.BLOQUEADA);
		}
		repository.salvar(ocorrencia);
		if (ocorrencia.getStatus() == OcorrenciaStatus.BLOQUEADA) {
			throw new ErroDeValidacaoDeOcorrenciaException(String.format(
					"O PIN fornecido é inválido. A ocorrência %s foi bloqueada devido 3 tentativas falhas de validação de PIN",
					ocorrencia.getID()));
		}
		throw new ErroDeValidacaoDeOcorrenciaException(String.format("O PIN fornecido é inválido"));
	}

	private Ocorrencia gerarAssinaturaEAtualizarOcorrencia(Ocorrencia ocorrencia, String PIN) {
		LocalDateTime timestamp = LocalDateTime.now();
		String dadosParaHash = ocorrencia.getID() + ocorrencia.getMatricula().getID() + timestamp + PIN + chaveSecreta.buscarChave();
		String assinatura = codificadorDeAssinatura.codificar(dadosParaHash);
		ocorrencia.getAssinaturaResponsavel().setResponsavelPelaColetaDaAssinatura(buscaFuncionario.buscarPeloUsuarioAutenticado());
		ocorrencia.getAssinaturaAluno().setTimestamp(timestamp);
		ocorrencia.getAssinaturaResponsavel().setAssinatura(assinatura);
		return ocorrencia;
	}

}
