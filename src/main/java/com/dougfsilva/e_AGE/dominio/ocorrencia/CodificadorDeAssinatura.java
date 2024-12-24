package com.dougfsilva.e_AGE.dominio.ocorrencia;

public interface CodificadorDeAssinatura {

	AssinaturaDeOcorrencia assinar(Ocorrencia ocorrencia);
	
	Boolean validar(Ocorrencia ocorrencia);
}
