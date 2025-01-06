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
@EqualsAndHashCode
@ToString
public class AssinaturaDeOcorrenciaManual {

	private String ID;
	private Boolean assinada;
	private Funcionario responsavelPelaAssinatura;	
	private LocalDateTime timestamp;
	private String justificativa;
	
	public AssinaturaDeOcorrenciaManual(Boolean assinada, Funcionario responsavelPelaAssinatura,
			LocalDateTime timestamp, String justificativa) {
		this.assinada = assinada;
		this.responsavelPelaAssinatura = responsavelPelaAssinatura;
		this.timestamp = timestamp;
		this.justificativa = justificativa;
	}
	
}
