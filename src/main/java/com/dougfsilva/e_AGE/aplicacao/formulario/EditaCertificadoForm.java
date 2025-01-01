package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EditaCertificadoForm(String ID, LocalDate data) {

	public EditaCertificadoForm {
        if (ID == null || ID.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
        }
    }
}
