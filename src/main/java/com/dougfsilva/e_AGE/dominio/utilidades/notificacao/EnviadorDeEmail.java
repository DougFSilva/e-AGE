package com.dougfsilva.e_AGE.dominio.utilidades.notificacao;

import java.util.List;

import com.dougfsilva.e_AGE.dominio.pessoa.Email;

public interface EnviadorDeEmail {

	void enviar(Email destinatario, List<Email> CC, String assunto, String corpo);

}
