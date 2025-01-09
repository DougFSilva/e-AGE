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
public class AssinaturaDeOcorrenciaAluno {

	private String ID;
	private String assinatura;
	private LocalDateTime timestamp;
	private Funcionario responsavelPelaColetaDaAssinatura;
	private String salt;
	
	public AssinaturaDeOcorrenciaAluno(String assinatura, LocalDateTime timestamp, Funcionario responsavelPelaColetaDaAssinatura, String salt) {
		this.assinatura = assinatura;
		this.timestamp = timestamp;
		this.responsavelPelaColetaDaAssinatura = responsavelPelaColetaDaAssinatura;
		this.salt = salt;
	}
	
	public boolean assinada() {
		return this.assinatura != null && !this.assinatura.isBlank();
	}
	
}
