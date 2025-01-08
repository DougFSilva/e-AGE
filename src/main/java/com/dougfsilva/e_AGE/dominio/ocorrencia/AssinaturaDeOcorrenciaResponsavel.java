package com.dougfsilva.e_AGE.dominio.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "assinatura" })
@ToString
public class AssinaturaDeOcorrenciaResponsavel {

	private String ID;
	private String assinatura;
	private String PIN;
	private LocalDateTime timestamp;
	private Integer tentativasFalhasDeAssinatura;
	private Funcionario responsavelPelaColetaDaAssinatura;
	
	public AssinaturaDeOcorrenciaResponsavel(String assinatura, String PIN, LocalDateTime timestamp, Funcionario responsavelPelaColetaDaAssinatura) {
		this.assinatura = assinatura;
		this.PIN = PIN;
		this.timestamp = timestamp;
		this.responsavelPelaColetaDaAssinatura = responsavelPelaColetaDaAssinatura;
	}

	public AssinaturaDeOcorrenciaResponsavel(String PIN) {
		this.PIN = PIN;
	}
	
	public boolean assinada() {
		return this.assinatura != null && !this.assinatura.isBlank();
	}
}
