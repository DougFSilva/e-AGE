package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.configuracao.BuscaConfiguracao;
import com.dougfsilva.e_AGE.dominio.configuracao.ChaveDeConfiguracao;
import com.dougfsilva.e_AGE.dominio.mensagem.celular.EnviadorDeMensagemDeCelular;
import com.dougfsilva.e_AGE.dominio.mensagem.email.EnviadorDeEmail;
import com.dougfsilva.e_AGE.dominio.mensagem.email.GeraConteudoDeEmail;
import com.dougfsilva.e_AGE.dominio.mensagem.email.MensagemDeEmail;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Cargo;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnviaNotificacaoDeOcorrencia {

	private final EnviadorDeEmail enviadorDeEmail;
	private final EnviadorDeMensagemDeCelular enviadorDeMensagemDeCelular;
	private final FuncionarioRepository funcionarioRepository;
	private final GeraConteudoDeEmail geraConteudoDeEmail;
	private final Map<ChaveDeConfiguracao, String> configs;

	public void aoAbrirOcorrencia(Ocorrencia ocorrencia) {
		String assunto = String.format("Nova ocorrência aberta para aluno %s", ocorrencia.getMatricula().getAluno().getNome());
		Email destinatario = ocorrencia.getMatricula().getAluno().getEmail();
		String corpo = "Olá, uma nova ocorrência foi aberta para o aluno abaixo"
				+ geraConteudoDeEmail.formatarDetalhesDaOcorrencia(ocorrencia)
				+ geraConteudoDeEmail.gerarInformacoesDeContato();
		MensagemDeEmail mensagemDeEmail = new MensagemDeEmail(destinatario, assunto, corpo);
		enviadorDeEmail.enviar(mensagemDeEmail);
		if (configs.get(ChaveDeConfiguracao.ENVIAR_MENSAGEM_CELULAR_AO_ABRIR_OCORRENCIA).equals("YES")) {
			enviadorDeMensagemDeCelular.enviar(Arrays.asList(ocorrencia.getMatricula().getAluno().getTelefone()), corpo);
		}
	}

	public void aoAbrirOcorrenciaComCopiaParaGestores(Ocorrencia ocorrencia) {

	}

	public void aoAbrirOcorrenciaComCopiaParaResponsavel(Ocorrencia ocorrencia) {

	}

	public void aoAbrirOcorrenciaParaResponsavelComPIN(Ocorrencia ocorrencia, String PIN) {

	}

	private List<Email> buscarEmailsDeGestores() {
		List<Email> emails = new ArrayList<Email>();
		emails.addAll(funcionarioRepository.buscarPeloCargo(Cargo.COORDENADOR).stream().map(Funcionario::getEmail).collect(Collectors.toList()));
		emails.addAll(funcionarioRepository.buscarPeloCargo(Cargo.AQV).stream().map(Funcionario::getEmail).collect(Collectors.toList()));
		return emails;
	}

}
