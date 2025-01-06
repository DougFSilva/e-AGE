package com.dougfsilva.e_AGE.dominio.mensagem.celular;

import java.util.List;

public interface EnviadorDeMensagemDeCelular {

	 void enviar(List<String> destinatarios, String mensagem);
}
