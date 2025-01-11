package com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura;

public interface PINService {
	
	String gerarPIN();

	String codificar(String PIN);
	
	boolean comparar(String PIN, String PINCodificado);
}
