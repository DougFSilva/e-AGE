package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.configuracao.BuscaConfiguracao;
import com.dougfsilva.e_AGE.dominio.configuracao.ChaveDeConfiguracao;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Cargo;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.notificacao.EnviadorDeEmail;
import com.dougfsilva.e_AGE.dominio.utilidades.notificacao.EnviadorDeMensagemDeCelular;

public class EnviaNotificacaoDeOcorrencia {

	private final EnviadorDeEmail enviadorDeEmail;
	private final EnviadorDeMensagemDeCelular enviadorDeMensagemDeCelular;
	private final BuscaConfiguracao buscaConfiguracao;
	private final FuncionarioRepository funcionarioRepository;
	private Map<ChaveDeConfiguracao, String> configuracoes;
	
	public EnviaNotificacaoDeOcorrencia(EnviadorDeEmail enviadorDeEmail,
			EnviadorDeMensagemDeCelular enviadorDeMensagemDeCelular, BuscaConfiguracao buscaConfiguracao, FuncionarioRepository funcionarioRepository) {
		this.enviadorDeEmail = enviadorDeEmail;
		this.enviadorDeMensagemDeCelular = enviadorDeMensagemDeCelular;
		this.buscaConfiguracao = buscaConfiguracao;
		this.configuracoes = buscaConfiguracao.buscarTodas();
		this.funcionarioRepository = funcionarioRepository;
	}

	public void aoAbrirOcorrencia(Ocorrencia ocorrencia) {
		this.configuracoes = buscaConfiguracao.buscarTodas();
		String assunto = String.format("Ocorrência aberta para aluno %s", ocorrencia.getMatricula().getAluno().getNome());
		String mensagemDeIntroducao = "Olá, uma nova ocorrência foi aberta:%n%n";
		if (configuracoes.get(ChaveDeConfiguracao.ENVIAR_EMAIL_AO_ABRIR_OCORRENCIA).equalsIgnoreCase("YES")) {
			enviarEmail(ocorrencia, assunto, mensagemDeIntroducao);
		}
		if (configuracoes.get(ChaveDeConfiguracao.ENVIAR_MENSAGEM_AO_ABRIR_OCORRENCIA).equalsIgnoreCase("YES")) {
			enviarMensagemCelular(ocorrencia, assunto, mensagemDeIntroducao);
		}
	}
	
	public void aoExcluirOcorrencia(Ocorrencia ocorrencia) {
		this.configuracoes = buscaConfiguracao.buscarTodas();
		String assunto = String.format("Ocorrência excluída para aluno %s", ocorrencia.getMatricula().getAluno().getNome());
		String mensagemDeIntroducao = "Olá, a ocorrência abaixo foi excluída:%n%n";
		if (configuracoes.get(ChaveDeConfiguracao.ENVIAR_EMAIL_AO_EXCLUIR_OCORRENCIA).equalsIgnoreCase("YES")) {
			enviarEmail(ocorrencia, assunto, mensagemDeIntroducao);
		}
		if (configuracoes.get(ChaveDeConfiguracao.ENVIAR_MENSAGEM_AO_EXCLUIR_OCORRENCIA).equalsIgnoreCase("YES")) {
			enviarMensagemCelular(ocorrencia, assunto, mensagemDeIntroducao);
		}
	}
	
	public void aoAtualizarOcorrencia(Ocorrencia ocorrencia) {
		this.configuracoes = buscaConfiguracao.buscarTodas();
		String assunto = String.format("Ocorrência atualizada para aluno %s", ocorrencia.getMatricula().getAluno().getNome());
		String mensagemDeIntroducao = "Olá, a ocorrência abaixo foi atualizada:%n%n";
		if (configuracoes.get(ChaveDeConfiguracao.ENVIAR_EMAIL_AO_ATUALIZAR_OCORRENCIA).equalsIgnoreCase("YES")) {
			enviarEmail(ocorrencia, assunto, mensagemDeIntroducao);
		}
		if (configuracoes.get(ChaveDeConfiguracao.ENVIAR_MENSAGEM_AO_ATUALIZAR_OCORRENCIA).equalsIgnoreCase("YES")) {
			enviarMensagemCelular(ocorrencia, assunto, mensagemDeIntroducao);
		}
	}
	
	public void aoEncerrarOcorrencia(Ocorrencia ocorrencia) {
		this.configuracoes = buscaConfiguracao.buscarTodas();
		String assunto = String.format("Ocorrência encerrada para aluno %s", ocorrencia.getMatricula().getAluno().getNome());
		String mensagemDeIntroducao = "Olá, a ocorrência abaixo foi encerrada:%n%n";
		if (configuracoes.get(ChaveDeConfiguracao.ENVIAR_EMAIL_AO_ENCERRAR_OCORRENCIA).equalsIgnoreCase("YES")) {
			enviarEmail(ocorrencia, assunto, mensagemDeIntroducao);
		}
		if (configuracoes.get(ChaveDeConfiguracao.ENVIAR_MENSAGEM_AO_ENCERRAR_OCORRENCIA).equalsIgnoreCase("YES")) {
			enviarMensagemCelular(ocorrencia, assunto, mensagemDeIntroducao);
		}
	}
	
	private void enviarEmail(Ocorrencia ocorrencia, String assunto, String mensagemDeIntroducao) {
		Email destinatario = ocorrencia.getMatricula().getAluno().getEmail();
		List<Email> cc = new ArrayList<>();
		if (ocorrencia.getEncaminhada() || ocorrencia.getMatricula().getAluno().calcularIdade() < 18) {
			cc.addAll(buscarEmailsDeGestores());
		}
		if (ocorrencia.getMatricula().getAluno().calcularIdade() < 18) {
			cc.add(ocorrencia.getMatricula().getAluno().getResponsavel().getEmail());
		}
		String corpo = mensagemDeIntroducao + construirDelalhesDaOcorrencia(ocorrencia) + construirInformacoesDeContato();
		enviadorDeEmail.enviar(destinatario, cc, assunto, corpo);
	}
	
	private void enviarMensagemCelular(Ocorrencia ocorrencia, String assunto, String mensagemDeIntroducao) {
		List<String> destinatarios = new ArrayList<>();
		destinatarios.add(ocorrencia.getMatricula().getAluno().getTelefone());
		if (ocorrencia.getMatricula().getAluno().calcularIdade() < 18) {
			destinatarios.add(ocorrencia.getMatricula().getAluno().getResponsavel().getTelefone());
		}
		String mensagem = assunto + construirDelalhesDaOcorrencia(ocorrencia) + construirInformacoesDeContato();
		enviadorDeMensagemDeCelular.enviar(destinatarios, mensagem);
	}
	
	private String construirDelalhesDaOcorrencia(Ocorrencia ocorrencia) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, HH:mm");
		String body = "Aluno: %s%n" 
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
				+ "Assinatura do aluno: %s%n" 
				+ "Fechado por: %s%n%n";
		
		return String.format(body,
				ocorrencia.getMatricula().getAluno().getNome(),
				ocorrencia.getTipo().name(),
				ocorrencia.getDescricao(),
				ocorrencia.getTratamento(),
				ocorrencia.getDataDeAbertura() != null ? ocorrencia.getDataDeAbertura().format(formatter) : null,
				ocorrencia.getDataDeFechamento() != null ? ocorrencia.getDataDeFechamento().format(formatter) : null,
				ocorrencia.getStatus().name(),
				ocorrencia.getID(),
				ocorrencia.getMatricula().getTurma().getCurso().getTitulo(),
				ocorrencia.getMatricula().getTurma().getCodigo(),
				ocorrencia.getRelator().getNome(),
				ocorrencia.getRelator().getCargo().name(),
				ocorrencia.getAssinatura(),
				ocorrencia.getResponsavelPeloFechamento().getNome());
	}
	
	private String construirInformacoesDeContato() {
		String info = "Atenciosamente, equipe escolar%n%n"
				+ "%s%n" // nome da escola
				+ "Email de contato: %s%n"
				+ "Telefone de contato: %s%n";
		return String.format(info, 
				configuracoes.get(ChaveDeConfiguracao.NOME_ESCOLA), 
				configuracoes.get(ChaveDeConfiguracao.EMAIL_DE_CONTATO),
				configuracoes.get(ChaveDeConfiguracao.TELEFONE_DE_CONTATO));
	}
	
	private List<Email> buscarEmailsDeGestores() {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios.addAll(funcionarioRepository.buscarPeloCargo(Cargo.COORDENADOR));
		funcionarios.addAll(funcionarioRepository.buscarPeloCargo(Cargo.AQV));
		return funcionarios.stream().map(Funcionario::getEmail).collect(Collectors.toList());
	}
	
}
