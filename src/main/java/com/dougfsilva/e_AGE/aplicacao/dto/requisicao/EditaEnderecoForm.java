package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

public record EditaEnderecoForm(String pais, String estado, String codigoPostal, String cidade, String bairro,
		String rua, String numero) {
}
