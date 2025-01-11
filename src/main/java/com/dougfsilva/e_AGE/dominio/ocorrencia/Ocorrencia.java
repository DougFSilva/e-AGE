package com.dougfsilva.e_AGE.dominio.ocorrencia;

import java.util.List;

import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.AssinaturaDeAluno;
import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.AssinaturaDeResponsavel;
import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.AssinaturaManual;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "ID" })
@ToString
public class Ocorrencia {

	private String ID;
	private Matricula matricula;
	private Modulo modulo;
	private TipoOcorrencia tipo;
	private Boolean encaminhada;
	private Boolean restrita;
	private String descricao;
	private List<TratamentoDeOcorrencia> tratamentos;
	private OcorrenciaStatus status;
	private AssinaturaDeAluno assinaturaAluno;
	private AssinaturaDeResponsavel assinaturaResponsavel;
	private AssinaturaManual assinaturaManual;
	private AberturaDeOcorrencia aberturaDeOcorrencia;
	private FechamentoDeOcorrencia fechamento;
	private EncerramentoDeOcorrencia encerramento;

	public Ocorrencia(AberturaDeOcorrencia aberturaDeOcorrencia, Funcionario relator, Matricula matricula, Modulo modulo, TipoOcorrencia tipo,
			Boolean encaminhada, Boolean restrita, String descricao) {
		this.aberturaDeOcorrencia = aberturaDeOcorrencia;
		this.matricula = matricula;
		this.tipo = tipo;
		this.encaminhada = encaminhada;
		this.restrita = restrita;
		this.descricao = descricao;
		this.status = OcorrenciaStatus.ABERTA;
	}
	
	public void addTratamento(TratamentoDeOcorrencia tratamento) {
		this.tratamentos.add(tratamento);
	}
	
	public void removeTratamento(TratamentoDeOcorrencia tratamento) {
		this.tratamentos.removeIf(t -> t.equals(tratamento));
	}

}
