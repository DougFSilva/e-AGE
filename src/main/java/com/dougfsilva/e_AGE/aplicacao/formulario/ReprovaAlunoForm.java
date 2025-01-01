package com.dougfsilva.e_AGE.aplicacao.formulario;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.dominio.curso.reprova.CausaReprova;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record ReprovaAlunoForm(String matriculaID, String moduloID, LocalDate data, CausaReprova causa) {

	public ReprovaAlunoForm {
        if (matriculaID == null || matriculaID.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo ID da matrícula não pode ser nulo ou vazio");
        }
        if (moduloID == null || moduloID.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo ID do módulo não pode ser nulo ou vazio");
        }
        if (data == null) {
            throw new ErroDeValidacaoDeCamposException("O campo data não pode ser nulo");
        }
        if (causa == null) {
            throw new ErroDeValidacaoDeCamposException("O campo causa não pode ser nulo");
        }
    }
}
