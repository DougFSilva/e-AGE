package com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura;

public interface CodificadorDeAssinatura {

	String codificar(String dadosParaAssinatura);
	
	boolean validarAssinatura(String assinatura, String assinaturaCodificada);
	
}
