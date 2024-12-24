package com.dougfsilva.e_AGE.dominio.utilidades.notificacao;

import com.dougfsilva.e_AGE.dominio.pessoa.Email;

public interface EnviadorDeEmail {

	void enviar(Email destinatario, String assunto, String corpo);

}
