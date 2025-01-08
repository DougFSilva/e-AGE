package com.dougfsilva.e_AGE.dominio.ocorrencia;

public interface CodificadorDeAssinatura {

	String codificar(String dadosParaAssinatura);
	
	boolean validarAssinatura(String dadosParaAssinatura, String assinaturaCodificada);
	
}
