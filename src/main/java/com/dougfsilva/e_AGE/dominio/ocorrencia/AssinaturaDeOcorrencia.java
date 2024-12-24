package com.dougfsilva.e_AGE.dominio.ocorrencia;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "assinatura", "data" })
public class AssinaturaDeOcorrencia {

	private String assinatura;
	private LocalDateTime data;
	private String salt;
}
