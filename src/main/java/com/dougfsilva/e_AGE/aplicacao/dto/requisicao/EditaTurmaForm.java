package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import java.time.LocalDate;

public record EditaTurmaForm(String ID, String codigo, String cursoID, LocalDate dataDeAbertura) {

}
