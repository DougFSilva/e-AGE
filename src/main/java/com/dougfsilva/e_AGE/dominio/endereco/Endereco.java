package com.dougfsilva.e_AGE.dominio.endereco;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "pais", "estado", "codigoPostal", "cidade", "numero" })
@ToString
public class Endereco {

	private String ID;
	private String pais;
	private String estado;
	private String codigoPostal;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;

	public Endereco(String pais, String estado, String codigoPostal, String cidade, String bairro, String rua,
			String numero) {
		this.pais = pais;
		this.estado = estado;
		this.codigoPostal = codigoPostal;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}

}
