package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.reprova.CausaReprova;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record EditaReprovaForm(String ID, String moduloID, LocalDate data, CausaReprova causa, String detalhes) {

	public EditaReprovaForm {
        if (ID == null || ID.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo ID não pode ser nulo ou vazio");
        }
    }
}
