package com.dougfsilva.e_AGE.aplicacao.casosdeuso.mensagem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemovePINDaMensagem {

	public static String remover(String mensagem) {
		Pattern pattern = Pattern.compile("\\[(.*?)\\]");
		Matcher matcher = pattern.matcher(mensagem);
		StringBuilder resultado = new StringBuilder();
		while (matcher.find()) {
			String conteudoDentroDosColchetes = matcher.group(1);
			String substituicao = "?".repeat(conteudoDentroDosColchetes.length());
			matcher.appendReplacement(resultado, "[" + substituicao + "]");
		}
		matcher.appendTail(resultado);
		return resultado.toString();
	}
}
