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
@EqualsAndHashCode(of = {"ID"})
@ToString
public class TratamentoDeOcorrencia {

	private String ID;
	private Funcionario tratador;
	private LocalDateTime timestamp;
	private String tratamento;
	
	public TratamentoDeOcorrencia(Funcionario tratador, LocalDateTime timestamp, String tratamento) {
		this.tratador = tratador;
		this.timestamp = timestamp;
		this.tratamento = tratamento;
	}
}
