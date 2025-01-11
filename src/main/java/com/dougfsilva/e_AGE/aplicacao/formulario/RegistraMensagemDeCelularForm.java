package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDateTime;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record RegistraMensagemDeCelularForm(String destinatario, List<String> CC, String mensagem,
		LocalDateTime dataHoraDeEnvio, Boolean enviadoComSucesso, String erro) {

	public RegistraMensagemDeCelularForm {
		if (destinatario == null || destinatario.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo destinatário não pode ser nulo");
		}
		if (mensagem == null || mensagem.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo mensagem não pode ser nulo ou vazio");
		}
		if (dataHoraDeEnvio == null) {
			throw new ErroDeValidacaoDeCamposException("O campo data e hora de envio não pode ser nulo");
		}
		if (enviadoComSucesso == null) {
			throw new ErroDeValidacaoDeCamposException("O campo enviado com sucesso não pode ser nulo");
		}
		if (enviadoComSucesso != null && !enviadoComSucesso && (erro == null || erro.isBlank())) {
			throw new ErroDeValidacaoDeCamposException("O campo erro deve ser preenchido quando o envio não for bem-sucedido");
		}
	}
}
