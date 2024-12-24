package com.dougfsilva.e_AGE.dominio.utilidades.imagem;

import org.springframework.web.multipart.MultipartFile;

public interface ImagemService {

	String salvar(MultipartFile image, TipoImagem tipo, String name);
	
	void remover(TipoImagem tipo, String name);
	
	String buscarImagemPadrao(TipoImagem tipo);
}
