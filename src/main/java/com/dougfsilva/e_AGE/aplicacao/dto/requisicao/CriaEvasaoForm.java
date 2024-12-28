package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaEvasaoForm(String matriculaID, String motivo) {

	public CriaEvasaoForm(String matriculaID, String motivo) {
        if (matriculaID == null || matriculaID.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo ID da matrícula não pode ser nulo ou vazio");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo motivo não pode ser nulo ou vazio");
        }
        this.matriculaID = matriculaID;
        this.motivo = motivo;
    }
}
