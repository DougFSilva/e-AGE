package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EvadeAlunoForm(String matriculaID, String moduloID, LocalDate data, String motivo) {

	public EvadeAlunoForm {
        if (matriculaID == null || matriculaID.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo ID da matrícula não pode ser nulo ou vazio");
        }
        if (moduloID == null || moduloID.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo ID do módulo não pode ser nulo ou vazio");
        }
        if (data == null) {
            throw new ErroDeValidacaoDeCamposException("O campo data não pode ser nulo");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo motivo não pode ser nulo ou vazio");
        }
    }
}
