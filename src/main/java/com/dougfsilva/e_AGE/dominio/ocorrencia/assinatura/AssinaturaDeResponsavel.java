package com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class AssinaturaDeResponsavel extends Assinatura {

	private String PIN;
	
	public AssinaturaDeResponsavel(String PIN) {
		this.PIN = PIN;
	}

	public AssinaturaDeResponsavel(LocalDateTime dataHora, String assinatura, Funcionario responsavelPelaColeta,
			String PIN) {
		super(dataHora, assinatura, responsavelPelaColeta);
		this.PIN = PIN;
	}
	
}
