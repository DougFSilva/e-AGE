package com.dougfsilva.e_AGE.dominio.ocorrencia;

import java.time.LocalDateTime;

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
public class AssinaturaDeOcorrenciaAluno {

	private String ID;
	private String assinatura;
	private LocalDateTime timestamp;
	private String salt;
	
	public AssinaturaDeOcorrenciaAluno(String assinatura, LocalDateTime timestamp, String salt) {
		this.assinatura = assinatura;
		this.timestamp = timestamp;
		this.salt = salt;
	}
	
}
