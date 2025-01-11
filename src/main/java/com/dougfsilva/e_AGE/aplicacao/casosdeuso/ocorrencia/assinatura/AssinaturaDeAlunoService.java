package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia.assinatura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.AssinaturaDeAluno;
import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.CodificadorDeAssinatura;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssinaturaDeAlunoService {
	
	private final CodificadorDeAssinatura codificadorDeAssinatura;

	public AssinaturaDeAluno gerarAssinaturaCodificada(String assinatura, Funcionario funcionarioAutenticado) {
		LocalDateTime dataHora = LocalDateTime.now();
		String dadosParaHash = funcionarioAutenticado.getID() + ":" + assinatura + ":" + dataHora.format(gerarFormatadorDeDataHora());
		String assinaturaCodificada = codificadorDeAssinatura.codificar(dadosParaHash);
		return new AssinaturaDeAluno(dataHora, assinaturaCodificada, funcionarioAutenticado);
	}
	
	public boolean validarAssinatura(AssinaturaDeAluno assinaturaDeAluno, String assinatura) {
		String dadosParaHash = assinaturaDeAluno.getResponsavelPelaColeta().getID() 
				+ ":" 
				+ assinatura 
				+ ":" 
				+ assinaturaDeAluno.getDataHora().format(gerarFormatadorDeDataHora());
		return codificadorDeAssinatura.validarAssinatura(dadosParaHash, assinaturaDeAluno.getAssinatura());
	}
	
	private DateTimeFormatter gerarFormatadorDeDataHora() {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	}
}
