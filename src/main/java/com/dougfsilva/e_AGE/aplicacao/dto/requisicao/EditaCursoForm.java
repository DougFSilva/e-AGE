package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import com.dougfsilva.e_AGE.dominio.curso.Modalidade;

public record EditaCursoForm(String ID, Modalidade modalidade, String areaTecnologicaID, String titulo, String descricao) {

}
