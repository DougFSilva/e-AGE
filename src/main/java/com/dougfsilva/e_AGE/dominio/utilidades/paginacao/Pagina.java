package com.dougfsilva.e_AGE.dominio.utilidades.paginacao;

import java.util.List;

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
public class Pagina<T> {

	private List<T> conteudo;
	private Integer numero;
	private Integer tamanho;
	private Long totalDeElementos;
	private Long totalDePaginas;
	private Boolean temConteudo;
	private Boolean primeira;
	private Boolean ultima;

}
