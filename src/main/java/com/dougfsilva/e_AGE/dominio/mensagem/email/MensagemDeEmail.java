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
@EqualsAndHashCode
@ToString
public class MensagemDeEmail {

	private Email destinatario;
	private List<Email> CC;
	private String assunto;
	private String corpo;
	private LocalDateTime dataHoraDeEnvio;
	private Boolean enviadoComSucesso;
	private String erro;
	
	public MensagemDeEmail(Email destinatario, String assunto, String corpo) {
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.corpo = corpo;
	}
	
}
