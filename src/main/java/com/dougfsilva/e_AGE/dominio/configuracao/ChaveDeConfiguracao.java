package com.dougfsilva.e_AGE.dominio.configuracao;

import java.util.List;

public enum ChaveDeConfiguracao {

	NOME_ESCOLA(null), 
	EMAIL_DE_CONTATO(null), 
	TELEFONE_DE_CONTATO(null),
	ENVIAR_EMAIL_AO_CRIAR_OCORRENCIA(List.of("SIM", "NÃO")), 
	ENVIAR_EMAIL_AO_EXCLUIR_OCORRENCIA(List.of("SIM", "NÃO")),
	ENVIAR_EMAIL_AO_ATUALIZAR_OCORRENCIA(List.of("SIM", "NÃO")),
	ENVIAR_EMAIL_AO_ENCERRAR_OCORRENCIA(List.of("SIM", "NÃO")),
	ENVIAR_MENSAGEM_AO_CRIAR_OCORRENCIA(List.of("SIM", "NÃO")),
	ENVIAR_MENSAGEM_AO_EXCLUIR_OCORRENCIA(List.of("SIM", "NÃO")),
	ENVIAR_MENSAGEM_AO_ATUALIZAR_OCORRENCIA(List.of("SIM", "NÃO")),
	ENVIAR_MENSAGEM_AO_ENCERRAR_OCORRENCIA(List.of("SIM", "NÃO"));

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
