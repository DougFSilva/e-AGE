package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.configuracao.ChaveDeConfiguracao;
import com.dougfsilva.e_AGE.dominio.mensagem.celular.EnviadorDeMensagemDeCelular;
import com.dougfsilva.e_AGE.dominio.mensagem.celular.MensagemDeCelular;
import com.dougfsilva.e_AGE.dominio.mensagem.email.EnviadorDeEmail;
import com.dougfsilva.e_AGE.dominio.mensagem.email.MensagemDeEmail;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnviaNotificacaoDeOcorrencia {

	private final EnviadorDeEmail enviadorDeEmail;
	private final EnviadorDeMensagemDeCelular enviadorDeMensagemDeCelular;
	private final FuncionarioRepository funcionarioRepository;
	private final Map<ChaveDeConfiguracao, String> configs;

	public void enviarNotificacaoParaAluno(Ocorrencia ocorrencia, OperacaoDeOcorrencia operacao) {
		Email destinatario = ocorrencia.getMatricula().getAluno().getEmail();
		enviarNotificacao(ocorrencia, destinatario, operacao, false, false, null);
	}

	public void enviarNotificacaoParaAlunoComCopiaParaGestores(Ocorrencia ocorrencia, OperacaoDeOcorrencia operacao) {
		Email destinatario = ocorrencia.getMatricula().getAluno().getEmail();
		enviarNotificacao(ocorrencia, destinatario, operacao, true, false, null);
	}

	public void enviarNotificacaoParaAlunoComCopiaParaResponsavel(Ocorrencia ocorrencia, OperacaoDeOcorrencia operacao) {
		Email destinatario = ocorrencia.getMatricula().getAluno().getEmail();
		enviarNotificacao(ocorrencia,destinatario, operacao, false, true, null);
	}

	public void enviarNotificacaoParaAlunoComCopiaParaGestoresEResponsavel(Ocorrencia ocorrencia, OperacaoDeOcorrencia operacao) {
		Email destinatario = ocorrencia.getMatricula().getAluno().getEmail();
		enviarNotificacao(ocorrencia, destinatario, operacao, true, true, null);
	}

	public void enviarNotificacaoParaResponsavelComPIN(Ocorrencia ocorrencia, OperacaoDeOcorrencia operacao, String PIN) {
		Email destinatario = ocorrencia.getMatricula().getAluno().getResponsavel().getEmail();
		enviarNotificacao(ocorrencia, destinatario, operacao, false, false, PIN);
	}

	public void enviarNotificacao(Ocorrencia ocorrencia, Email destinatario, OperacaoDeOcorrencia operacao, boolean copiaGestores, boolean copiaResponsavel, String PIN) {
		String assunto = gerarAssunto(ocorrencia, operacao);
		String introducao = gerarIntroducao(ocorrencia, operacao);
		String corpo = introducao
				+ gerarDetalhesDaOcorrencia(ocorrencia) 
				+ PIN != null ? gerarMensagemComPIN(PIN): "" 
				+ gerarInformacoesDeContato();
		MensagemDeEmail mensagemDeEmail = new MensagemDeEmail(destinatario, assunto, corpo);
		if (copiaGestores) {
			mensagemDeEmail.addAllCC(buscarEmailsDeGestores());
		}
		if (copiaResponsavel) {
			mensagemDeEmail.addCC(ocorrencia.getMatricula().getAluno().getResponsavel().getEmail());
		}
		enviadorDeEmail.enviar(mensagemDeEmail);
		if (configs.get(ChaveDeConfiguracao.ENVIAR_MENSAGEM_CELULAR_AO_OPERAR_OCORRENCIA).equals("YES")) {
			MensagemDeCelular mensagemDeCelular = new MensagemDeCelular(ocorrencia.getMatricula().getAluno().getTelefone(), corpo);
			if (copiaResponsavel) {
				mensagemDeCelular.addCC(ocorrencia.getMatricula().getAluno().getResponsavel().getTelefone());
			}
			enviadorDeMensagemDeCelular.enviar(mensagemDeCelular);
		}
	}

	private List<Email> buscarEmailsDeGestores() {
		return funcionarioRepository.buscarPeloPerfilDoUsuario(TipoPerfil.GESTOR)
				.stream()
				.map(Funcionario::getEmail)
				.collect(Collectors.toList());
	}
	
	private String gerarAssunto(Ocorrencia ocorrencia, OperacaoDeOcorrencia operacao) {
		switch (operacao) {
			case ABERTA: {
				return String.format("Ocorrência %s - Nova ocorrência aberta para aluno %s", 
						ocorrencia.getID(), ocorrencia.getMatricula().getAluno().getNome());
			}
			case ATUALIZADA: {
				return String.format("Ocorrência %s - Atualizada ocorrência do aluno %s", 
						ocorrencia.getID(), ocorrencia.getMatricula().getAluno().getNome());
			}
			case FECHADA: {
				return String.format("Ocorrência %s - Fechada ocorrência do aluno %s", 
						ocorrencia.getID(), ocorrencia.getMatricula().getAluno().getNome());
			}
			case ENCERRADA: {
				return String.format("Ocorrência %s - Encerrada ocorrência do aluno %s", 
						ocorrencia.getID(), ocorrencia.getMatricula().getAluno().getNome());
			}
			case NOVO_PIN_GERADO: {
				return String.format("Ocorrência %s - Novo PIN gerado", ocorrencia.getID());
			}
			case EXCLUIDA: {
				return String.format("Ocorrência %s - Excluída ocorrência do aluno %s", 
						ocorrencia.getID(), ocorrencia.getMatricula().getAluno().getNome());
			}
			default:
				throw new IllegalArgumentException("Valor inesperado de operação: " + operacao);
			}
	}
	
	private String gerarIntroducao(Ocorrencia ocorrencia, OperacaoDeOcorrencia operacao) {
		switch (operacao) {
			case ABERTA: {
				return String.format("Olá, uma nova ocorrência foi aberta para o aluno %s. Detalhes abaixo:%n%n", 
						ocorrencia.getMatricula().getAluno().getNome());
			}
			case ATUALIZADA: {
				return String.format("Olá, a ocorrência %s do aluno %s foi atualizada. Detalhes abaixo:%n%n", 
						ocorrencia.getID(), ocorrencia.getMatricula().getAluno().getNome());
			}
			case FECHADA: {
				return String.format("Olá, a ocorrência %s do aluno %s foi fechada. Detalhes abaixo:%n%n", 
						ocorrencia.getID(), ocorrencia.getMatricula().getAluno().getNome());
			}
			case ENCERRADA: {
				return String.format("Olá, a ocorrência %s do aluno %s foi encerrada. Detalhes abaixo:%n%n", 
						ocorrencia.getID(), ocorrencia.getMatricula().getAluno().getNome());
			}
			case NOVO_PIN_GERADO: {
				return String.format("Olá, um novo PIN para assinatura da ocorrência %s foi gerado. Detalhes abaixo:%n%n", ocorrencia.getID());
			}
			case EXCLUIDA: {
				return String.format("Olá, a ocorrência %s do aluno %s foi excluída. Detalhes abaixo:%n%n", 
						ocorrencia.getID(), ocorrencia.getMatricula().getAluno().getNome());
			}
			default:
				throw new IllegalArgumentException("Valor inesperado de operação: " + operacao);
			}
	}

	private String gerarMensagemComPIN(String PIN) {
		return String.format(
				"Ao comparecer à escola para assinatura da ocorrência, por favor, apresentar o seguinte PIN: %s%n%n", PIN);
	}

	private String gerarDetalhesDaOcorrencia(Ocorrencia ocorrencia) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, HH:mm");
		String corpo = "Aluno: %s%n" 
				+ "Tipo: %s%n" 
				+ "Descrição: %s%n" 
				+ "Tratamento: %s%n" 
				+ "Data de abertura: %s%n"
				+ "Data de fechamento: %s%n" 
				+ "Status: %s%n%n" 
				
				+ "Detalhes da ocorrência:%n%n" 
				+ "ID: %s%n"
				+ "Curso: %s%n" 
				+ "Turma: %s%n" 
				+ "Relator: %s (%s)%n" 
				+ "Assinatura do aluno: %s (%s)%n"
				+ "Assinatura do responsável: %s (%s)%n" 
				+ "Fechado por: %s%n%n";

		return String.format(corpo, 
				ocorrencia.getMatricula().getAluno().getNome(), 
				ocorrencia.getTipo().name(),
				ocorrencia.getDescricao(), 
				ocorrencia.getTratamento(),
				ocorrencia.getAberturaDeOcorrencia().getDataHora() != null ? ocorrencia.getAberturaDeOcorrencia().getDataHora().format(formatter) : null,
				ocorrencia.getEncerramento().getDataHora() != null ? ocorrencia.getEncerramento().getDataHora().format(formatter): null,
				ocorrencia.getStatus().name(), 
				
				ocorrencia.getID(),
				ocorrencia.getMatricula().getTurma().getCurso().getTitulo(),
				ocorrencia.getMatricula().getTurma().getCodigo(), 
				ocorrencia.getAberturaDeOcorrencia().getRelator().getNome(),
				ocorrencia.getAberturaDeOcorrencia().getRelator().getCargo().name(), 
				ocorrencia.getAssinaturaAluno().getAssinatura(),
				ocorrencia.getAssinaturaAluno().getTimestamp() != null? ocorrencia.getAssinaturaAluno().getTimestamp().format(formatter): null,
				ocorrencia.getAssinaturaResponsavel().getAssinatura(),
				ocorrencia.getAssinaturaResponsavel().getTimestamp() != null ? ocorrencia.getAssinaturaResponsavel().getTimestamp().format(formatter) : null,
				ocorrencia.getFechamento().getResponsavel().getNome());
	}

	private String gerarInformacoesDeContato() {
		String info = "Atenciosamente, equipe escolar%n%n" 
				+ "Escola: %s%n" 
				+ "Email de contato: %s%n"
				+ "Telefone de contato: %s%n";
		return String.format(info, 
				configs.get(ChaveDeConfiguracao.NOME_ESCOLA),
				configs.get(ChaveDeConfiguracao.EMAIL_DE_CONTATO),
				configs.get(ChaveDeConfiguracao.TELEFONE_DE_CONTATO));
	}

}
