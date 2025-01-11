package com.dougfsilva.e_AGE.aplicacao.casosdeuso.mensagem.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dougfsilva.e_AGE.aplicacao.formulario.RegistraMensagemDeEmailForm;
import com.dougfsilva.e_AGE.dominio.mensagem.email.MensagemDeEmail;
import com.dougfsilva.e_AGE.dominio.mensagem.email.MensagemDeEmailRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistraEmailEnviado {

	private final MensagemDeEmailRepository repository;
	
	public MensagemDeEmail registrar(RegistraMensagemDeEmailForm form) {
		String corpo = removerPINDoCorpoDaMensagem(form.corpo());
		MensagemDeEmail mensagemDeEmail = new MensagemDeEmail(form.destinatario(), 
				form.CC(), 
				form.assunto(), 
				corpo, 
				form.dataHoraDeEnvio(), 
				form.enviadoComSucesso(), 
				form.erro());
		return repository.salvar(mensagemDeEmail);
	}
	
	private String removerPINDoCorpoDaMensagem(String corpo) {
		  Pattern pattern = Pattern.compile("\\[(.*?)\\]");
	        Matcher matcher = pattern.matcher(corpo);
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
