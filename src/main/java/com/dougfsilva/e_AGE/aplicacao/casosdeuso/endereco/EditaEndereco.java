package com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco;

import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.EditaEnderecoForm;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;

public class EditaEndereco {

	public Endereco editar(EditaEnderecoForm form, Endereco endereco) {
		if (form.pais() != null && !form.pais().isBlank()) {
			endereco.setPais(form.pais());
		}
		if (form.estado() != null && !form.estado().isBlank()) {
			endereco.setEstado(form.estado());
		}
		if (form.codigoPostal() != null && !form.codigoPostal().isBlank()) {
			endereco.setCodigoPostal(form.codigoPostal());
		}
		if (form.cidade() != null && !form.cidade().isBlank()) {
			endereco.setCidade(form.cidade());
		}
		if (form.bairro() != null && !form.bairro().isBlank()) {
			endereco.setBairro(form.bairro());
		}
		if (form.rua() != null && !form.rua().isBlank()) {
			endereco.setRua(form.rua());
		}
		if (form.numero() != null && !form.numero().isBlank()) {
			endereco.setNumero(form.numero());
		}
		return endereco;
	}
}
