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
public class AssinaturaManual extends Assinatura{

	private String justificativa;

	public AssinaturaManual(LocalDateTime dataHora, String assinatura, Funcionario responsavelPelaColeta, String justificativa) {
		super(dataHora, assinatura, responsavelPelaColeta);
		this.justificativa = justificativa;
	}
	
}
