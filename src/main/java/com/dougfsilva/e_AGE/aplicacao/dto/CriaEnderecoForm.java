package com.dougfsilva.e_AGE.aplicacao.dto;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;

public record CriaEnderecoForm(String pais, String estado, String codigoPostal, String cidade, String bairro,
		String rua, String numero) {

	public CriaEnderecoForm(String pais, String estado, String codigoPostal, String cidade, String bairro, String rua,
			String numero) {
		if (pais == null || pais.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo país não pode ser nulo ou vazio");
		}
		if (estado == null || estado.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo estado não pode ser nulo ou vazio");
		}
		if (codigoPostal == null || codigoPostal.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo código postal não pode ser nulo ou vazio");
		}
		if (cidade == null || cidade.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo cidade não pode ser nulo ou vazio");
		}
		if (bairro == null || bairro.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo bairro não pode ser nulo ou vazio");
		}
		if (rua == null || rua.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo rua não pode ser nulo ou vazio");
		}
		if (numero == null || numero.isBlank()) {
			throw new ErroDeValidacaoDeCamposException("O campo número não pode ser nulo ou vazio");
		}
		this.pais = pais;
		this.estado = estado;
		this.codigoPostal = codigoPostal;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}
}
