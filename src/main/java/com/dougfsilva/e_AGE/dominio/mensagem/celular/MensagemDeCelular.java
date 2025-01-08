package com.dougfsilva.e_AGE.dominio.mensagem.celular;

import java.time.LocalDateTime;
import java.util.List;

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
public class MensagemDeCelular {

	private String ID;
	private String destinatario;
	private List<String> CC;
	private String mensagem;
	private LocalDateTime dataHoraDeEnvio;
	private Boolean enviadoComSucesso;
	private String erro;
	
	public MensagemDeCelular(String destinatario,  String mensagem) {
		this.destinatario = destinatario;
		this.mensagem = mensagem;
	}

	public void addCC(String telefone) {
		this.CC.add(telefone);
	}

	public void addAllCC(List<String> telefone) {
		this.CC.addAll(telefone);
	}
}
