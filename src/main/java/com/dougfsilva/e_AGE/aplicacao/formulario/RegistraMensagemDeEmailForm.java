package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDateTime;
import java.util.List;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;

public record RegistraMensagemDeEmailForm(Email destinatario, List<Email> CC, String assunto, String corpo,
		LocalDateTime dataHoraDeEnvio, Boolean enviadoComSucesso, String erro) {

	public RegistraMensagemDeEmailForm {
		if (destinatario == null) {
			throw new ErroDeValidacaoDeCamposException("O campo destinatário não pode ser nulo");
		}
		if (assunto == null || assunto.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo assunto não pode ser nulo ou vazio");
		}
		if (corpo == null || corpo.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo corpo não pode ser nulo ou vazio");
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
		if (CC == null) {
			throw new ErroDeValidacaoDeCamposException("O campo CC não pode ser nulo");
		}
	}
}
