package com.dougfsilva.e_AGE.dominio.utilidades.paginacao;

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
public class RequisicaoDePagina {

	private Integer page;
	private Integer size;
	private Direcao direcao;
}
