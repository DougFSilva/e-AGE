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
public class AssinaturaDeAluno extends Assinatura {

	public AssinaturaDeAluno(LocalDateTime dataHora, String assinatura, Funcionario responsavelPelaColeta) {
		super(dataHora, assinatura, responsavelPelaColeta);
	}
	
}
