package com.dougfsilva.e_AGE.dominio.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "ID" })
public class Ocorrencia {

	private String ID;
	private LocalDateTime dataDeAbertura;
	private LocalDateTime dataDeFechamento;
	private Funcionario relator;
	private Matricula matricula;
	private TipoOcorrencia tipo;
	private Boolean encaminhada;
	private Boolean restrita;
	private String descricao;
	private String tratamento;
	private OcorrenciaStatus status;
	private AssinaturaDeOcorrencia assinatura;
	private Funcionario responsavelPeloFechamento;

	public Ocorrencia(LocalDateTime dataDeAbertura, Funcionario relator, Matricula matricula, TipoOcorrencia tipo,
			Boolean encaminhada, Boolean restrita, String descricao) {
		this.dataDeAbertura = dataDeAbertura;
		this.relator = relator;
		this.matricula = matricula;
		this.tipo = tipo;
		this.encaminhada = encaminhada;
		this.restrita = restrita;
		this.descricao = descricao;
		this.status = OcorrenciaStatus.ABERTA;
	}

}
