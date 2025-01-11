package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia.assinatura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.AssinaturaDeResponsavel;
import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.CodificadorDeAssinatura;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssinaturaDeResponsavelService {

	private final CodificadorDeAssinatura codificadorDeAssinatura;

	public AssinaturaDeResponsavel gerarAssinaturaCodificada(String PIN, Funcionario funcionarioAutenticado) {
		LocalDateTime dataHora = LocalDateTime.now();
		String dadosParaHash = funcionarioAutenticado.getID() + ":" +  PIN + dataHora.format(gerarFormatadorDeDataHora());
		String assinaturaCodificada = codificadorDeAssinatura.codificar(dadosParaHash);
		return new AssinaturaDeResponsavel(dataHora, assinaturaCodificada, funcionarioAutenticado, PIN);
	}

	public boolean validarAssinatura(AssinaturaDeResponsavel assinaturaDeResponsavel, String assinatura) {
		String dadosParaHash = assinaturaDeResponsavel.getResponsavelPelaColeta().getID() 
				+ ":" 
				+ assinatura 
				+ ":" 
				+ assinaturaDeResponsavel.getDataHora().format(gerarFormatadorDeDataHora());
		return codificadorDeAssinatura.validarAssinatura(dadosParaHash, assinaturaDeResponsavel.getAssinatura());

	}
	
	private DateTimeFormatter gerarFormatadorDeDataHora() {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	}

}
