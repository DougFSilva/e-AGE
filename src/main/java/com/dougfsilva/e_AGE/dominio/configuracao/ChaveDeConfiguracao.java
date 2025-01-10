package com.dougfsilva.e_AGE.dominio.configuracao;

import java.util.List;

public enum ChaveDeConfiguracao {

	NOME_ESCOLA(null), 
	EMAIL_DE_CONTATO(null), 
	TELEFONE_DE_CONTATO(null),
	ENVIAR_MENSAGEM_CELULAR_AO_OPERAR_OCORRENCIA(List.of("SIM", "NAO"));

	private final List<String> valoresPermitidos;

	ChaveDeConfiguracao(List<String> valoresPermitidos) {
		this.valoresPermitidos = valoresPermitidos;
	}

	public List<String> getValoresPermitidos() {
		return valoresPermitidos;
	}

	public void validarValor(String valor) {
		if (valoresPermitidos != null && !valoresPermitidos.contains(valor)) {
			throw new IllegalArgumentException("Valor de configuração inválido: " + valor);
		}
	}

}
