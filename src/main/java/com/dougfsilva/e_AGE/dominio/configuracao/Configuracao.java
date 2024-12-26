package com.dougfsilva.e_AGE.dominio.configuracao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = { "chave" })
@ToString
public class Configuracao {

	private String ID;
	private ChaveDeConfiguracao chave;
	private String valor;

	public Configuracao(ChaveDeConfiguracao chave, String valor) {
		this.chave = chave;
		this.chave.validarValor(valor);
		this.valor = valor;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public void setChave(ChaveDeConfiguracao chave) {
		this.chave = chave;
	}

	public void setValor(String valor) {
		this.chave.validarValor(valor);
		this.valor = valor;
	}

}
