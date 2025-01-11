package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia.assinatura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.AssinaturaManual;
import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.CodificadorDeAssinatura;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssinaturaManualService {

	private final CodificadorDeAssinatura codificadorDeAssinatura;

	public AssinaturaManual gerarAssinaturaCodificada(String assinatura, Funcionario funcionarioAutenticado, String justificativa) {
		LocalDateTime dataHora = LocalDateTime.now();
		String dadosParaHash = funcionarioAutenticado.getID() + ":" + assinatura + ":" + dataHora.format(gerarFormatadorDeDataHora());
		String assinaturaCodificada = codificadorDeAssinatura.codificar(dadosParaHash);
		return new AssinaturaManual(dataHora, assinaturaCodificada, funcionarioAutenticado, justificativa);
	}
	
	public boolean validarAssinatura(AssinaturaManual assinaturaManual, String assinatura) {
		String dadosParaHash = assinaturaManual.getResponsavelPelaColeta().getID() 
				+ ":" 
				+ assinatura 
				+ ":" 
				+ assinaturaManual.getDataHora().format(gerarFormatadorDeDataHora());
		return codificadorDeAssinatura.validarAssinatura(dadosParaHash, assinaturaManual.getAssinatura());
	}
	
	private DateTimeFormatter gerarFormatadorDeDataHora() {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	}
	
}
