package com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"ID"})
@ToString
public abstract class Assinatura {

	private String ID;
	private String assinatura;
	private LocalDateTime dataHora;
	private Funcionario responsavelPelaColeta;
	
	public Assinatura(LocalDateTime dataHora, String assinatura, Funcionario responsavelPelaColeta) {
		this.dataHora = dataHora;
		this.assinatura = assinatura;
		this.responsavelPelaColeta = responsavelPelaColeta;
	}
	
	public boolean assinada() {
		return this.assinatura != null && !this.assinatura.isBlank();
	}
	
}
