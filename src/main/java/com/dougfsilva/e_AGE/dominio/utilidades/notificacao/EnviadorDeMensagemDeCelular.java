package com.dougfsilva.e_AGE.dominio.utilidades.notificacao;

import java.util.List;

public interface EnviadorDeMensagemDeCelular {

	 void enviar(List<String> destinatarios, String mensagem);
}
