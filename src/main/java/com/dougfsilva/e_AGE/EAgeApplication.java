package com.dougfsilva.e_AGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EAgeApplication {

	public static void main(String[] args) {
		//SpringApplication.run(EAgeApplication.class, args);
		 String texto = "Ao comparecer à escola PIN:[123A45] para assinatura da ocorrência, por favor, apresentar o seguinte PIN:[123A45]";

	        // Regex para capturar o conteúdo dentro dos colchetes
	        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
	        Matcher matcher = pattern.matcher(texto);

	        // Substitui o conteúdo dentro dos colchetes por '?' de acordo com o número de caracteres
	        StringBuilder resultado = new StringBuilder();

	        // Percorre o texto original
	        while (matcher.find()) {
	            String conteudoDentroDosColchetes = matcher.group(1);  // Obtém o conteúdo dentro dos colchetes
	            String substituicao = "?".repeat(conteudoDentroDosColchetes.length());  // Gera a string com '?'s
	            matcher.appendReplacement(resultado, "[" + substituicao + "]");
	        }
	        matcher.appendTail(resultado);  // Adiciona o restante do texto

	        System.out.println(resultado.toString());
	}

}
