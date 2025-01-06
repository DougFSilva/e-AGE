package com.dougfsilva.e_AGE.dominio.mensagem.email;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.dougfsilva.e_AGE.dominio.configuracao.ChaveDeConfiguracao;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GeraConteudoDeEmail {
	
	private final Map<ChaveDeConfiguracao, String> configs;
	
	public String formatarDetalhesDaOcorrencia(Ocorrencia ocorrencia) {
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
				+ "Assinatura do aluno: %s (%s)%n" 
				+ "Assinatura do responsável: %s (%s)%n"
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
				ocorrencia.getAssinaturaAluno().getAssinatura(),
				ocorrencia.getAssinaturaAluno().getTimestamp() != null ? ocorrencia.getAssinaturaAluno().getTimestamp().format(formatter) : null,
				ocorrencia.getAssinaturaResponsavel().getAssinatura(),
				ocorrencia.getAssinaturaResponsavel().getTimestamp() != null ? ocorrencia.getAssinaturaResponsavel().getTimestamp().format(formatter) : null,
				ocorrencia.getResponsavelPeloFechamento().getNome());
	}
	
	public String gerarInformacoesDeContato() {
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
