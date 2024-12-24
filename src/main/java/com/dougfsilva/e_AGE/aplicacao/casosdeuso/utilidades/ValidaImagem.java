package com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeImagemException;

public class ValidaImagem {

	public void validar(MultipartFile imagem) {
		if (imagem == null || imagem.isEmpty()) {
			throw new ErroDeValidacaoDeImagemException("A imagem não pode ser nula ou vazia");
		}
	}
}
