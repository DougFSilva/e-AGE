package com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco;

import com.dougfsilva.e_AGE.aplicacao.dto.CriaEnderecoForm;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;

public class CriaEndereco {

	public Endereco criar(CriaEnderecoForm form) {
		return new Endereco(
				form.pais(),
				form.estado(),
				form.codigoPostal(),
				form.cidade(),
				form.bairro(),
				form.rua(),
				form.numero());
	}
}
