package com.dougfsilva.e_AGE.dominio.mensagem.email;

import java.time.LocalDateTime;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.pessoa.Email;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "ID" })
@ToString
public class MensagemDeEmail {

	private String ID;
	private Email destinatario;
	private List<Email> CC;
	private String assunto;
	private String corpo;
	private LocalDateTime dataHoraDeEnvio;
	private Boolean enviadoComSucesso;
	private String erro;
	
	public MensagemDeEmail(Email destinatario, List<Email> CC, String assunto, String corpo,
			LocalDateTime dataHoraDeEnvio, Boolean enviadoComSucesso, String erro) {
		this.destinatario = destinatario;
		this.CC = CC;
		this.assunto = assunto;
		this.corpo = corpo;
		this.dataHoraDeEnvio = dataHoraDeEnvio;
		this.enviadoComSucesso = enviadoComSucesso;
		this.erro = erro;
	}

	public MensagemDeEmail(Email destinatario, String assunto, String corpo) {
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.corpo = corpo;
	}

	public void addCC(Email email) {
		this.CC.add(email);
	}

	public void addAllCC(List<Email> email) {
		this.CC.addAll(email);
	}

}
