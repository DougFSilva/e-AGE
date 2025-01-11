package com.dougfsilva.e_AGE.aplicacao.casosdeuso.mensagem.celular;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.mensagem.RemovePINDaMensagem;
import com.dougfsilva.e_AGE.aplicacao.formulario.RegistraMensagemDeCelularForm;
import com.dougfsilva.e_AGE.dominio.mensagem.celular.MensagemDeCelular;
import com.dougfsilva.e_AGE.dominio.mensagem.celular.MensagemDeCelularRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistraMensagemDeCelularEnviada {

	private final MensagemDeCelularRepository repository;

	public MensagemDeCelular registrar(RegistraMensagemDeCelularForm form) {
		String mensagem = RemovePINDaMensagem.remover(form.mensagem());
		MensagemDeCelular mensagemDeCelular = new MensagemDeCelular(form.destinatario(), form.CC(), mensagem,
				form.dataHoraDeEnvio(), form.enviadoComSucesso(), form.erro());
		return repository.salvar(mensagemDeCelular);
	}

}
