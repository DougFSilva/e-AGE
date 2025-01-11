package com.dougfsilva.e_AGE.aplicacao.casosdeuso.mensagem.email;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.mensagem.RemovePINDaMensagem;
import com.dougfsilva.e_AGE.aplicacao.formulario.RegistraMensagemDeEmailForm;
import com.dougfsilva.e_AGE.dominio.mensagem.email.MensagemDeEmail;
import com.dougfsilva.e_AGE.dominio.mensagem.email.MensagemDeEmailRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistraMensagemDeEmailEnviado {

	private final MensagemDeEmailRepository repository;

	public MensagemDeEmail registrar(RegistraMensagemDeEmailForm form) {
		String corpo = RemovePINDaMensagem.remover(form.corpo());
		MensagemDeEmail mensagemDeEmail = new MensagemDeEmail(form.destinatario(), form.CC(), form.assunto(), corpo,
				form.dataHoraDeEnvio(), form.enviadoComSucesso(), form.erro());
		return repository.salvar(mensagemDeEmail);
	}

}
